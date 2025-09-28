
package app.application.port.in;

import app.domain.model.MedicalRecord;
import app.domain.model.Order;
import app.domain.model.VitalSigns;
import java.util.List;

/**
 * Define las operaciones de atención médica
 * Usada por roles DOCTOR y NURSE
 */
public interface MedicalAttentionUseCase {
    
    // === PARA MÉDICOS ===
    
    /**
     * Crea un registro en la historia clínica
     * @param medicalRecord Los datos del registro médico
     * @return El registro creado
     */
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
    
    /**
     * Busca la historia clínica de un paciente
     * @param patientId Cédula del paciente
     * @return Lista de registros médicos
     */
    List<MedicalRecord> getPatientMedicalHistory(String patientId);
    
    /**
     * Crea una orden médica (medicamentos, procedimientos o ayudas diagnósticas)
     * @param order Los datos de la orden
     * @return La orden creada
     */
    Order createMedicalOrder(Order order);
    
    /**
     * Busca las órdenes de un paciente
     * @param patientId Cédula del paciente
     * @return Lista de órdenes
     */
    List<Order> getPatientOrders(String patientId);
    
    // === PARA ENFERMERAS ===
    
    /**
     * Registra signos vitales de un paciente
     * @param vitalSigns Los signos vitales a registrar
     * @return Los signos vitales registrados
     */
    VitalSigns recordVitalSigns(VitalSigns vitalSigns);
    
    /**
     * Busca los signos vitales de un paciente
     * @param patientId Cédula del paciente
     * @return Lista de registros de signos vitales
     */
    List<VitalSigns> getPatientVitalSigns(String patientId);
    
    /**
     * Registra la administración de un medicamento
     * @param orderNumber Número de la orden
     * @param itemNumber Número del ítem en la orden
     * @param nurseId Cédula de la enfermera
     * @return true si se registró correctamente
     */
    boolean recordMedicineAdministration(String orderNumber, Integer itemNumber, String nurseId);
}