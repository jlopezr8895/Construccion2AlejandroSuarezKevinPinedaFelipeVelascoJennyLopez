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

/*La interfaz PatientJpaRepository es el repositorio JPA que gestiona la persistencia de pacientes en la base de datos.
Hereda de JpaRepository, por lo que ya trae todos los métodos CRUD básicos listos para usar.
Define consultas personalizadas como findByUsername y existsByUsername, que Spring convierte 
automáticamente en SQL sin necesidad de escribirlas manualmente.
Gracias a esta interfaz, el sistema puede guardar, buscar, verificar y eliminar pacientes en la 
tabla patients de MySQL de manera sencilla.*/