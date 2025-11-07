package app.infrastructure.persistence.entity.jpa;

import app.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByPatientId(String patientId);
    List<OrderEntity>  findByDoctorId(String doctorId);
}