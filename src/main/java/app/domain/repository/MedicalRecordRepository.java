package app.domain.repository;

import app.domain.model.MedicalRecord;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository {
    MedicalRecord save(MedicalRecord medicalRecord);
    Optional<MedicalRecord> findById(String id);
    List<MedicalRecord> findByPatientId(String patientId);
    List<MedicalRecord> findByDoctorId(String doctorId);
}