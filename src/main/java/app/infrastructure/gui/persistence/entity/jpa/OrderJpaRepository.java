package app.infrastructure.gui.persistence.entity.jpa;

import app.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByPatientId(String patientId);
    List<OrderEntity> findByDoctorId(String doctorId);
}

/*La interfaz OrderJpaRepository es un repositorio de Spring Data JPA que conecta directamente con la base de datos.
Hereda de JpaRepository, por lo que ya tiene implementados métodos CRUD básicos sin escribir código.
Además, define métodos personalizados como findByPatientId y findByDoctorId, que Spring traduce 
automáticamente en consultas SQL gracias al nombre del método.
En resumen, esta interfaz permite acceder y manipular las órdenes en la base de datos de manera
sencilla, sin necesidad de escribir consultas SQL manuales.*/