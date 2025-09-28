package app.infrastructure.gui.persistence.entity.jpa;

import app.domain.model.valueObjects.Role;
import app.infrastructure.gui.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findByRole(Role role);
}