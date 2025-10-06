package app.application.port.in;


import app.domain.model.Medicine;
import app.domain.model.Procedure;
import app.domain.model.DiagnosticAid;
import java.util.List;

/**
 * Define las operaciones de gestión de inventarios
 * Usada por el rol INFORMATION_SUPPORT
 */
public interface InventoryManagementUseCase {
    
    // === MEDICAMENTOS ===
    Medicine createMedicine(Medicine medicine);
    List<Medicine> getAllMedicines();
    Medicine findMedicineById(String id);
    Medicine updateMedicine(Medicine medicine);
    boolean deleteMedicine(String id);
    
    // === PROCEDIMIENTOS ===
    Procedure createProcedure(Procedure procedure);
    List<Procedure> getAllProcedures();
    Procedure findProcedureById(String id);
    Procedure updateProcedure(Procedure procedure);
    boolean deleteProcedure(String id);
    
    // === AYUDAS DIAGNÓSTICAS ===
    DiagnosticAid createDiagnosticAid(DiagnosticAid diagnosticAid);
    List<DiagnosticAid> getAllDiagnosticAids();
    DiagnosticAid findDiagnosticAidById(String id);
    DiagnosticAid updateDiagnosticAid(DiagnosticAid diagnosticAid);
    boolean deleteDiagnosticAid(String id);
}