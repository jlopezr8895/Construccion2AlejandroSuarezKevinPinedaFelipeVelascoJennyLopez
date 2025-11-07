
package app.infrastructure.persistence.adapter;

import app.domain.model.MedicalRecord;
import app.domain.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementación SIMPLE en memoria para MedicalRecordRepository
 * Para evitar crear entidades JPA adicionales
 */
@Repository
public class MedicalRecordRepositoryAdapter implements MedicalRecordRepository {
    
    // Almacenamiento en memoria (se pierde al reiniciar)
    private final ConcurrentHashMap<String, MedicalRecord> records = new ConcurrentHashMap<>();
    
    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        records.put(medicalRecord.getId(), medicalRecord);
        return medicalRecord;
    }
    
    @Override
    public Optional<MedicalRecord> findById(String id) {
        return Optional.ofNullable(records.get(id));
    }
    
    @Override
    public List<MedicalRecord> findByPatientId(String patientId) {
        return records.values().stream()
                .filter(record -> patientId.equals(record.getPatientId()))
                .toList();
    }
    
    @Override
    public List<MedicalRecord> findByDoctorId(String doctorId) {
        return records.values().stream()
                .filter(record -> doctorId.equals(record.getDoctorId()))
                .toList();
    }
}