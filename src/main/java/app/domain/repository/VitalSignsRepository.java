package app.domain.repository;

import app.domain.model.VitalSigns;
import java.util.List;
import java.util.Optional;

public interface VitalSignsRepository {
    VitalSigns save(VitalSigns vitalSigns);
    Optional<VitalSigns> findById(String id);
    List<VitalSigns> findByPatientId(String patientId);
    List<VitalSigns> findByNurseId(String nurseId);
    List<VitalSigns> findAll();
}
