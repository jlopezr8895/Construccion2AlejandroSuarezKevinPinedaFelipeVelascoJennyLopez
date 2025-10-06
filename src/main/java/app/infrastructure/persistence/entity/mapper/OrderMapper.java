package app.infrastructure.persistence.entity.mapper;

import app.domain.model.Order;
import app.infrastructure.persistence.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    
    public OrderEntity toEntity(Order order) {
        if (order == null) return null;
        return new OrderEntity(
            order.getOrderNumber(),
            order.getPatientId(),
            order.getDoctorId(),
            order.getCreationDate(),
            order.getOrderType()
        );
    }
    
    public Order toDomain(OrderEntity orderEntity) {
        if (orderEntity == null) return null;
        return new Order(
            orderEntity.getOrderNumber(),
            orderEntity.getPatientId(),
            orderEntity.getDoctorId(),
            orderEntity.getCreationDate(),
            orderEntity.getOrderType()
        );
    }
}