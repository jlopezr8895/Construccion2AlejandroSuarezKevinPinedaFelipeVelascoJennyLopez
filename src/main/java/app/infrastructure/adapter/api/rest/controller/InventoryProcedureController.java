package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.InventoryManagementUseCase;
import app.domain.model.Procedure;
import app.infrastructure.adapter.api.rest.dto.inventory.CreateProcedureRequest;
import app.infrastructure.adapter.api.rest.dto.inventory.ProcedureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@org.springframework.security.access.prepost.PreAuthorize("hasRole('INFORMATION_SUPPORT')")
@RequestMapping("/api/inventory/procedures")
public class InventoryProcedureController {

    private final InventoryManagementUseCase inventoryManagementUseCase;

    public InventoryProcedureController(InventoryManagementUseCase inventoryManagementUseCase) {
        this.inventoryManagementUseCase = inventoryManagementUseCase;
    }

    @PostMapping
    public ResponseEntity<ProcedureResponse> createProcedure(@RequestBody CreateProcedureRequest request) {
        Procedure procedure = new Procedure(
            request.getId(),
            request.getName(),
            request.getDescription(),
            request.getCost(),
            request.isRequiresSpecialist()
        );

        Procedure created = inventoryManagementUseCase.createProcedure(procedure);
        ProcedureResponse response = mapToProcedureResponse(created);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProcedureResponse>> getAllProcedures() {
        List<Procedure> procedures = inventoryManagementUseCase.getAllProcedures();
        List<ProcedureResponse> response = procedures.stream()
            .map(this::mapToProcedureResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedureResponse> getProcedureById(@PathVariable String id) {
        Procedure procedure = inventoryManagementUseCase.findProcedureById(id);

        if (procedure == null) {
            return ResponseEntity.notFound().build();
        }

        ProcedureResponse response = mapToProcedureResponse(procedure);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedureResponse> updateProcedure(
            @PathVariable String id,
            @RequestBody CreateProcedureRequest request) {
        
        Procedure procedure = new Procedure(
            id,
            request.getName(),
            request.getDescription(),
            request.getCost(),
            request.isRequiresSpecialist()
        );

        Procedure updated = inventoryManagementUseCase.updateProcedure(procedure);
        ProcedureResponse response = mapToProcedureResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedure(@PathVariable String id) {
        boolean deleted = inventoryManagementUseCase.deleteProcedure(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    private ProcedureResponse mapToProcedureResponse(Procedure procedure) {
        return new ProcedureResponse(
            procedure.getId(),
            procedure.getName(),
            procedure.getDescription(),
            procedure.getCost(),
            procedure.isRequiresSpecialist()
        );
    }
}
