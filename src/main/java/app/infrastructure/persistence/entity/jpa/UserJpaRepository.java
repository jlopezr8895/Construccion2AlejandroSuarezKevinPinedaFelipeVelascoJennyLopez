package app.infrastructure.persistence.entity.jpa;

import app.domain.model.valueObjects.Role;
import app.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findByRole(Role role);
    Optional<UserEntity> findByEmail(String email);
}