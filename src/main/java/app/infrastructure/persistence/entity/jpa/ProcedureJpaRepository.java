package app.infrastructure.persistence.entity.jpa;

import app.infrastructure.persistence.entity.ProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProcedureJpaRepository extends JpaRepository<ProcedureEntity, String> {
    List<ProcedureEntity> findByNameContaining(String name);
    List<ProcedureEntity> findByRequiresSpecialist(Boolean requiresSpecialist);
}
