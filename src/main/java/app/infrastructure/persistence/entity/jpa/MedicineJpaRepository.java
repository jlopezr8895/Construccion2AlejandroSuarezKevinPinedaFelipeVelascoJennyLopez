package app.infrastructure.persistence.entity.jpa;

import app.infrastructure.persistence.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicineJpaRepository extends JpaRepository<MedicineEntity, String> {
    List<MedicineEntity> findByNameContaining(String name);
}
