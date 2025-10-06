package app.infrastructure.persistence.entity.jpa;

import app.infrastructure.persistence.entity.VitalSignsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VitalSignsJpaRepository extends JpaRepository<VitalSignsEntity, String> {
    List<VitalSignsEntity> findByPatientId(String patientId);
    List<VitalSignsEntity> findByNurseId(String nurseId);
}