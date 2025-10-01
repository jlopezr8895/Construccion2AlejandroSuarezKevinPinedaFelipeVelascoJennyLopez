package app.domain.repository;

import app.domain.model.MedicalRecord;
import java.util.List;
import java.util.Optional;


/*define las operaciones necesarias para manejar las historias clínicas en el sistema
Al ser una interfaz, no tiene la implementación todavía, solo dice qué se puede hacer. 
Más adelante, en la capa de infraestructura, se implementará usando JPA*/
public interface MedicalRecordRepository {
    MedicalRecord save(MedicalRecord medicalRecord);
    Optional<MedicalRecord> findById(String id);
    List<MedicalRecord> findByPatientId(String patientId);
    List<MedicalRecord> findByDoctorId(String doctorId);
}