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

/*La interfaz UserJpaRepository es un repositorio JPA para manejar usuarios.
Gracias a JpaRepository, no necesitas escribir código para operaciones CRUD básicas, Spring lo genera automáticamente.
Además, define un método personalizado findByRole, que obtiene todos los usuarios con un rol 
específico, como los administradores o los pacientes.
En conclusión, esta interfaz permite acceder a los usuarios en la tabla users de la base de 
datos MySQL de manera sencilla, sin escribir consultas SQL manuales.*/