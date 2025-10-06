package app.infrastructure.persistence.entity.jpa;

import app.infrastructure.persistence.entity.DiagnosticAidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiagnosticAidJpaRepository extends JpaRepository<DiagnosticAidEntity, String> {
    List<DiagnosticAidEntity> findByNameContaining(String name);
    List<DiagnosticAidEntity> findByRequiresSpecialist(Boolean requiresSpecialist);
}