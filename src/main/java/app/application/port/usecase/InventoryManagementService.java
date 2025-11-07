
package app.application.port.usecase;

import app.application.port.in.InventoryManagementUseCase;
import app.domain.model.Medicine;
import app.domain.model.Procedure;
import app.domain.model.DiagnosticAid;
import app.domain.repository.MedicineRepository;
import app.domain.repository.ProcedureRepository;
import app.domain.repository.DiagnosticAidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryManagementService implements InventoryManagementUseCase {
    
    private final MedicineRepository medicineRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticAidRepository diagnosticAidRepository;
    
    @Autowired
    public InventoryManagementService(MedicineRepository medicineRepository,
                                     ProcedureRepository procedureRepository,
                                     DiagnosticAidRepository diagnosticAidRepository) {
        this.medicineRepository = medicineRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticAidRepository = diagnosticAidRepository;
    }
    
    // === MEDICAMENTOS ===
    
    @Override
    public Medicine createMedicine(Medicine medicine) {
        if (medicine.getId() == null || medicine.getId().isEmpty()) {
            throw new IllegalArgumentException("El ID del medicamento es requerido");
        }
        if (medicineRepository.existsById(medicine.getId())) {
            throw new IllegalArgumentException("Ya existe un medicamento con ID: " + medicine.getId());
        }
        return medicineRepository.save(medicine);
    }
    
    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    
    @Override
    public Medicine findMedicineById(String id) {
        return medicineRepository.findById(id).orElse(null);
    }
    
    @Override
    public Medicine updateMedicine(Medicine medicine) {
        if (!medicineRepository.existsById(medicine.getId())) {
            throw new IllegalArgumentException("No existe medicamento con ID: " + medicine.getId());
        }
        return medicineRepository.save(medicine);
    }
    
    @Override
    public boolean deleteMedicine(String id) {
        if (!medicineRepository.existsById(id)) {
            return false;
        }
        medicineRepository.deleteById(id);
        return true;
    }
    
    // === PROCEDIMIENTOS ===
    
    @Override
    public Procedure createProcedure(Procedure procedure) {
        if (procedure.getId() == null || procedure.getId().isEmpty()) {
            throw new IllegalArgumentException("El ID del procedimiento es requerido");
        }
        if (procedureRepository.existsById(procedure.getId())) {
            throw new IllegalArgumentException("Ya existe un procedimiento con ID: " + procedure.getId());
        }
        return procedureRepository.save(procedure);
    }
    
    @Override
    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }
    
    @Override
    public Procedure findProcedureById(String id) {
        return procedureRepository.findById(id).orElse(null);
    }
    
    @Override
    public Procedure updateProcedure(Procedure procedure) {
        if (!procedureRepository.existsById(procedure.getId())) {
            throw new IllegalArgumentException("No existe procedimiento con ID: " + procedure.getId());
        }
        return procedureRepository.save(procedure);
    }
    
    @Override
    public boolean deleteProcedure(String id) {
        if (!procedureRepository.existsById(id)) {
            return false;
        }
        procedureRepository.deleteById(id);
        return true;
    }
    
    // === AYUDAS DIAGNÓSTICAS ===
    
    @Override
    public DiagnosticAid createDiagnosticAid(DiagnosticAid diagnosticAid) {
        if (diagnosticAid.getId() == null || diagnosticAid.getId().isEmpty()) {
            throw new IllegalArgumentException("El ID de la ayuda diagnóstica es requerido");
        }
        if (diagnosticAidRepository.existsById(diagnosticAid.getId())) {
            throw new IllegalArgumentException("Ya existe una ayuda diagnóstica con ID: " + diagnosticAid.getId());
        }
        return diagnosticAidRepository.save(diagnosticAid);
    }
    
    @Override
    public List<DiagnosticAid> getAllDiagnosticAids() {
        return diagnosticAidRepository.findAll();
    }
    
    @Override
    public DiagnosticAid findDiagnosticAidById(String id) {
        return diagnosticAidRepository.findById(id).orElse(null);
    }
    
    @Override
    public DiagnosticAid updateDiagnosticAid(DiagnosticAid diagnosticAid) {
        if (!diagnosticAidRepository.existsById(diagnosticAid.getId())) {
            throw new IllegalArgumentException("No existe ayuda diagnóstica con ID: " + diagnosticAid.getId());
        }
        return diagnosticAidRepository.save(diagnosticAid);
    }
    
    @Override
    public boolean deleteDiagnosticAid(String id) {
        if (!diagnosticAidRepository.existsById(id)) {
            return false;
        }
        diagnosticAidRepository.deleteById(id);
        return true;
    }
}