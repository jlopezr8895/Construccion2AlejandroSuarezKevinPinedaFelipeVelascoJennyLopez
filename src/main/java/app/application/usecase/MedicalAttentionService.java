
package app.application.usecase;

import app.application.port.in.MedicalAttentionUseCase;
import app.domain.model.MedicalRecord;
import app.domain.model.Order;
import app.domain.model.VitalSigns;
import app.domain.repository.MedicalRecordRepository;
import app.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MedicalAttentionService implements MedicalAttentionUseCase {
    
    private final MedicalRecordRepository medicalRecordRepository;
    private final OrderRepository orderRepository;
    
    @Autowired
    public MedicalAttentionService(MedicalRecordRepository medicalRecordRepository,
                                  OrderRepository orderRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.orderRepository = orderRepository;
    }
    
    // === IMPLEMENTACIONES PARA MÉDICOS ===
    
    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        // Generar ID único si no tiene
        if (medicalRecord.getId() == null || medicalRecord.getId().isEmpty()) {
            medicalRecord.setId(UUID.randomUUID().toString());
        }
        
        // Establecer fecha actual si no tiene
        if (medicalRecord.getDate() == null) {
            medicalRecord.setDate(LocalDate.now());
        }
        
        return medicalRecordRepository.save(medicalRecord);
    }
    
    @Override
    public List<MedicalRecord> getPatientMedicalHistory(String patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }
    
    @Override
    public Order createMedicalOrder(Order order) {
        // Validar que la orden sea válida
        if (!order.isValid()) {
            throw new IllegalArgumentException("Los datos de la orden no son válidos");
        }
        
        // Verificar que no exista una orden con el mismo número
        if (orderRepository.existsByOrderNumber(order.getOrderNumber())) {
            throw new IllegalArgumentException("Ya existe una orden con el número: " + order.getOrderNumber());
        }
        
        // Establecer fecha actual si no tiene
        if (order.getCreationDate() == null) {
            order.setCreationDate(LocalDate.now());
        }
        
        return orderRepository.save(order);
    }
    
    @Override
    public List<Order> getPatientOrders(String patientId) {
        return orderRepository.findByPatientId(patientId);
    }
    
    // === IMPLEMENTACIONES PARA ENFERMERAS ===
    
    @Override
    public VitalSigns recordVitalSigns(VitalSigns vitalSigns) {
        // Generar ID único si no tiene
        if (vitalSigns.getId() == null || vitalSigns.getId().isEmpty()) {
            vitalSigns.setId(UUID.randomUUID().toString());
        }
        
        // Establecer fecha/hora actual si no tiene
        if (vitalSigns.getRecordDate() == null) {
            vitalSigns.setRecordDate(LocalDateTime.now());
        }
        
        // NOTA: Para simplificar, guardamos como string en logs
        // En un proyecto real, tendrías un VitalSignsRepository
        System.out.println("📊 Signos vitales registrados: " + vitalSigns.getId() + 
                          " para paciente: " + vitalSigns.getPatientId());
        
        return vitalSigns;
    }
    
    @Override
    public List<VitalSigns> getPatientVitalSigns(String patientId) {
        // NOTA: Para simplificar, retornamos lista vacía
        // En un proyecto real, tendrías un VitalSignsRepository
        System.out.println("🔍 Buscando signos vitales para paciente: " + patientId);
        return List.of(); // Lista vacía por simplicidad
    }
    
    @Override
    public boolean recordMedicineAdministration(String orderNumber, Integer itemNumber, String nurseId) {
        // Verificar que la orden existe
        if (!orderRepository.existsByOrderNumber(orderNumber)) {
            return false;
        }
        
        // NOTA: Para simplificar, solo registramos en logs
        // En un proyecto real, tendrías una tabla de administración de medicamentos
        System.out.println("💊 Medicamento administrado - Orden: " + orderNumber + 
                          ", Item: " + itemNumber + ", Enfermera: " + nurseId);
        
        return true;
    }
}