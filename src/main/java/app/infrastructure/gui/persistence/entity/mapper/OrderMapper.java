package app.infrastructure.gui.persistence.entity.mapper;

import app.domain.model.Order;
import app.infrastructure.persistence.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component

/*OrderMapper no cambia la información, solo la adapta al formato 
correcto para cada contexto: dominio (lógica de negocio) o persistencia (base de datos).*/
public class OrderMapper {
    
    public OrderEntity toEntity(Order order) {//Inglés 
        if (order == null) return null;
        return new OrderEntity(
            order.getOrderNumber(),
            order.getPatientId(),
            order.getDoctorId(),
            order.getCreationDate(),
            order.getOrderType()
        );
    }
    
    public Order toDomain(OrderEntity orderEntity) {//Español
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

/*El OrderMapper funciona como un traductor entre la lógica de la aplicación y 
la base de datos. La clase Order representa el ‘idioma’ que entiende nuestra 
aplicación, mientras que OrderEntity representa el ‘idioma’ que entiende la base 
de datos. El mapper convierte entre estos dos formatos para que cada capa trabaje 
con la información de forma correcta sin depender directamente de la otra.*/