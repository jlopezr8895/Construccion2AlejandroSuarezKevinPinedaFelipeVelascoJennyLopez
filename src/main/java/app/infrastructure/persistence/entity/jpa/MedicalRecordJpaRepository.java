package app.infrastructure.persistence.entity.jpa;

import app.infrastructure.persistence.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicalRecordJpaRepository extends JpaRepository<MedicalRecordEntity, String> {
    List<MedicalRecordEntity> findByPatientId(String patientId);
    List<MedicalRecordEntity> findByDoctorId(String doctorId);
}
