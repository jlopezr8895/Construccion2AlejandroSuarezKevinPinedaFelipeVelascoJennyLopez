package app.infrastructure.gui.persistence.entity.jpa;

import app.infrastructure.gui.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientJpaRepository extends JpaRepository<PatientEntity, String> {
    Optional<PatientEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}