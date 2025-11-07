package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.InventoryManagementUseCase;
import app.domain.model.Medicine;
import app.infrastructure.adapter.api.rest.dto.inventory.CreateMedicineRequest;
import app.infrastructure.adapter.api.rest.dto.inventory.MedicineResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@org.springframework.security.access.prepost.PreAuthorize("hasRole('INFORMATION_SUPPORT')")
@RequestMapping("/api/inventory/medicines")
public class InventoryMedicineController {

    private final InventoryManagementUseCase inventoryManagementUseCase;

    public InventoryMedicineController(InventoryManagementUseCase inventoryManagementUseCase) {
        this.inventoryManagementUseCase = inventoryManagementUseCase;
    }

    @PostMapping
    public ResponseEntity<MedicineResponse> createMedicine(@RequestBody CreateMedicineRequest request) {
        Medicine medicine = new Medicine(
            request.getId(),
            request.getName(),
            request.getDescription(),
            request.getCost()
        );

        Medicine created = inventoryManagementUseCase.createMedicine(medicine);
        MedicineResponse response = mapToMedicineResponse(created);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponse>> getAllMedicines() {
        List<Medicine> medicines = inventoryManagementUseCase.getAllMedicines();
        List<MedicineResponse> response = medicines.stream()
            .map(this::mapToMedicineResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponse> getMedicineById(@PathVariable String id) {
        Medicine medicine = inventoryManagementUseCase.findMedicineById(id);

        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }

        MedicineResponse response = mapToMedicineResponse(medicine);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponse> updateMedicine(
            @PathVariable String id,
            @RequestBody CreateMedicineRequest request) {
        
        Medicine medicine = new Medicine(
            id,
            request.getName(),
            request.getDescription(),
            request.getCost()
        );

        Medicine updated = inventoryManagementUseCase.updateMedicine(medicine);
        MedicineResponse response = mapToMedicineResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable String id) {
        boolean deleted = inventoryManagementUseCase.deleteMedicine(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    private MedicineResponse mapToMedicineResponse(Medicine medicine) {
        return new MedicineResponse(
            medicine.getId(),
            medicine.getName(),
            medicine.getDescription(),
            medicine.getCost()
        );
    }
}
