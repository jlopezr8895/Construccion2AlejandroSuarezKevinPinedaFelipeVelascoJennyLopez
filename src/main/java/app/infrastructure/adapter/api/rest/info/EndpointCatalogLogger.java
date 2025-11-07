package app.infrastructure.adapter.api.rest.info;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EndpointCatalogLogger implements ApplicationListener<ApplicationReadyEvent> {

    private final RequestMappingHandlerMapping handlerMapping;

    public EndpointCatalogLogger(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            printEndpointsWithRoles();
        } catch (Exception e) {
            System.out.println("[EndpointCatalog] No se pudo listar endpoints: " + e.getMessage());
        }
    }

    private void printEndpointsWithRoles() {
        Map<String, String> baseRole = new LinkedHashMap<>();
        baseRole.put("/api/auth", "PUBLIC");
        baseRole.put("/api/users", "HUMAN_RESOURCES");
        baseRole.put("/api/patients", "ADMINISTRATIVE");
        baseRole.put("/api/inventory", "INFORMATION_SUPPORT");
        baseRole.put("/api/nursing", "NURSE");
        baseRole.put("/api/medical", "DOCTOR");

        List<EndpointInfo> endpoints = new ArrayList<>();

        Map<RequestMappingInfo, HandlerMethod> methods = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methods.entrySet()) {
            RequestMappingInfo info = entry.getKey();

            Set<String> patterns = extractPatterns(info);
            Set<String> httpMethods = info.getMethodsCondition().getMethods().stream()
                    .map(Enum::name)
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            if (httpMethods.isEmpty()) {
                // Si no hay métodos específicos, considerar ALL
                httpMethods = new LinkedHashSet<>(Collections.singletonList("ALL"));
            }

            for (String pattern : patterns) {
                if (!pattern.startsWith("/api")) continue;
                String role = resolveRoleForPath(pattern, baseRole);
                for (String method : httpMethods) {
                    endpoints.add(new EndpointInfo(method, pattern, role));
                }
            }
        }

        // Ordenar por path y luego por método
        endpoints.sort(Comparator
                .comparing(EndpointInfo::path)
                .thenComparing(EndpointInfo::method));

        // Encabezado bonito
        System.out.println("\n========================================");
        System.out.println(" API REST disponible - Endpoints & Roles ");
        System.out.println("========================================");
        System.out.println("Rol por base:");
        baseRole.forEach((base, role) -> System.out.println(" - " + base + " -> " + ("PUBLIC".equals(role) ? "PUBLIC" : ("ROLE_" + role))));
        System.out.println("----------------------------------------");
        System.out.println(String.format("%-8s %-55s %-20s", "METHOD", "PATH", "REQUIRED ROLE"));
        System.out.println("--------------------------------------------------------------------------");
        for (EndpointInfo ep : dedupe(endpoints)) {
            String roleOut = "PUBLIC".equals(ep.role) ? "PUBLIC" : ("ROLE_" + ep.role);
            System.out.println(String.format("%-8s %-55s %-20s", ep.method, ep.path, roleOut));
        }
        System.out.println("--------------------------------------------------------------------------\n");
    }

    private List<EndpointInfo> dedupe(List<EndpointInfo> list) {
        LinkedHashMap<String, EndpointInfo> map = new LinkedHashMap<>();
        for (EndpointInfo ep : list) {
            map.put(ep.method + " " + ep.path, ep);
        }
        return new ArrayList<>(map.values());
    }

    private String resolveRoleForPath(String path, Map<String, String> baseRole) {
        // Buscar el base-path más largo que coincida
        String bestBase = null;
        for (String base : baseRole.keySet()) {
            if (path.startsWith(base)) {
                if (bestBase == null || base.length() > bestBase.length()) {
                    bestBase = base;
                }
            }
        }
        return bestBase == null ? "PUBLIC" : baseRole.get(bestBase);
    }

    private Set<String> extractPatterns(RequestMappingInfo info) {
        Set<String> patterns = new LinkedHashSet<>();
        try {
            // Spring 6 / Boot 3
            if (info.getPathPatternsCondition() != null) {
                info.getPathPatternsCondition().getPatterns().forEach(p -> patterns.add(p.getPatternString()));
            }
        } catch (NoSuchMethodError ignored) {}
        try {
            // Compatibilidad con implementación basada en AntPatterns
            if (patterns.isEmpty() && info.getPatternsCondition() != null) {
                info.getPatternsCondition().getPatterns().forEach(p -> patterns.add(p.toString()));
            }
        } catch (NoSuchMethodError ignored) {}
        return patterns;
    }

    private record EndpointInfo(String method, String path, String role) {}
}
