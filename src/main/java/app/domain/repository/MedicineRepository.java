package app.domain.repository;

import app.domain.model.Medicine;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository {
    Medicine save(Medicine medicine);
    Optional<Medicine> findById(String id);
    List<Medicine> findAll();
    List<Medicine> findByNameContaining(String name);
    boolean existsById(String id);
    void deleteById(String id);
}