package app.domain.repository;

import app.domain.model.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);
    Optional<Patient> findById(String id);
    List<Patient> findAll();
    Optional<Patient> findByUsername(String username);
    void deleteById(String id);
    boolean existsById(String id);
    boolean existsByUsername(String username);
}