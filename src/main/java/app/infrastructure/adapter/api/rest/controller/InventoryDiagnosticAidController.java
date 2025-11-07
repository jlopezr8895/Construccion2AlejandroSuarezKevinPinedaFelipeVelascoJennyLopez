package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.InventoryManagementUseCase;
import app.domain.model.DiagnosticAid;
import app.infrastructure.adapter.api.rest.dto.inventory.CreateDiagnosticAidRequest;
import app.infrastructure.adapter.api.rest.dto.inventory.DiagnosticAidResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@org.springframework.security.access.prepost.PreAuthorize("hasRole('INFORMATION_SUPPORT')")
@RequestMapping("/api/inventory/diagnostic-aids")
public class InventoryDiagnosticAidController {

    private final InventoryManagementUseCase inventoryManagementUseCase;

    public InventoryDiagnosticAidController(InventoryManagementUseCase inventoryManagementUseCase) {
        this.inventoryManagementUseCase = inventoryManagementUseCase;
    }

    @PostMapping
    public ResponseEntity<DiagnosticAidResponse> createDiagnosticAid(@RequestBody CreateDiagnosticAidRequest request) {
        DiagnosticAid diagnosticAid = new DiagnosticAid(
            request.getId(),
            request.getName(),
            request.getDescription(),
            request.getCost(),
            request.isRequiresSpecialist()
        );

        DiagnosticAid created = inventoryManagementUseCase.createDiagnosticAid(diagnosticAid);
        DiagnosticAidResponse response = mapToDiagnosticAidResponse(created);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticAidResponse>> getAllDiagnosticAids() {
        List<DiagnosticAid> diagnosticAids = inventoryManagementUseCase.getAllDiagnosticAids();
        List<DiagnosticAidResponse> response = diagnosticAids.stream()
            .map(this::mapToDiagnosticAidResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticAidResponse> getDiagnosticAidById(@PathVariable String id) {
        DiagnosticAid diagnosticAid = inventoryManagementUseCase.findDiagnosticAidById(id);

        if (diagnosticAid == null) {
            return ResponseEntity.notFound().build();
        }

        DiagnosticAidResponse response = mapToDiagnosticAidResponse(diagnosticAid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticAidResponse> updateDiagnosticAid(
            @PathVariable String id,
            @RequestBody CreateDiagnosticAidRequest request) {
        
        DiagnosticAid diagnosticAid = new DiagnosticAid(
            id,
            request.getName(),
            request.getDescription(),
            request.getCost(),
            request.isRequiresSpecialist()
        );

        DiagnosticAid updated = inventoryManagementUseCase.updateDiagnosticAid(diagnosticAid);
        DiagnosticAidResponse response = mapToDiagnosticAidResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnosticAid(@PathVariable String id) {
        boolean deleted = inventoryManagementUseCase.deleteDiagnosticAid(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    private DiagnosticAidResponse mapToDiagnosticAidResponse(DiagnosticAid diagnosticAid) {
        return new DiagnosticAidResponse(
            diagnosticAid.getId(),
            diagnosticAid.getName(),
            diagnosticAid.getDescription(),
            diagnosticAid.getCost(),
            diagnosticAid.isRequiresSpecialist()
        );
    }
}
