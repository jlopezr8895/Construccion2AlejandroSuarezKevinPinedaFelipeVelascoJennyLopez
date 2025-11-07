
package app.application.port.in;

import app.domain.model.VitalSigns;
import java.util.List;

/**
 * Define las operaciones de enfermería
 * Usada por el rol NURSE
 */
public interface NursingUseCase {
    
    /**
     * Registra signos vitales de un paciente
     */
    VitalSigns recordVitalSigns(VitalSigns vitalSigns);
    
    /**
     * Obtiene todos los signos vitales de un paciente
     */
    List<VitalSigns> getPatientVitalSigns(String patientId);
    
    /**
     * Obtiene los signos vitales registrados por una enfermera
     */
    List<VitalSigns> getVitalSignsByNurse(String nurseId);
    
    /**
     * Busca signos vitales por ID
     */
    VitalSigns findVitalSignsById(String id);
}
