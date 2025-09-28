package app.domain.repository;

import app.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByOrderNumber(String orderNumber);
    List<Order> findByPatientId(String patientId);
    List<Order> findByDoctorId(String doctorId);
    boolean existsByOrderNumber(String orderNumber);
}