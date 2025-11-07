package app.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Endpoint público de login
                .requestMatchers("/api/auth/login").permitAll()
                
                // Recursos Humanos - Gestión de Usuarios
                .requestMatchers("/api/users/**").hasRole("HUMAN_RESOURCES")
                
                // Personal Administrativo - Gestión de Pacientes
                .requestMatchers("/api/patients/**").hasRole("ADMINISTRATIVE")
                
                // Soporte de Información - Gestión de Inventarios
                .requestMatchers("/api/inventory/**").hasRole("INFORMATION_SUPPORT")
                
                // Enfermería - Signos Vitales
                .requestMatchers("/api/nursing/**").hasRole("NURSE")
                
                // Médicos - Atención Médica
                .requestMatchers("/api/medical/**").hasRole("DOCTOR")
                
                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        // Agregar el filtro JWT antes del filtro de autenticación de Spring
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
