package app.infrastructure.gui.persistence.adapter;

import app.domain.model.Order;
import app.domain.repository.OrderRepository;
import app.infrastructure.persistence.entity.OrderEntity;
import app.infrastructure.gui.persistence.entity.jpa.OrderJpaRepository;
import app.infrastructure.gui.persistence.entity.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//orderMapper encargado de convertir entre objeto del dominio (Order) y entidad JPA (OrderEntity).

@Repository
public class OrderRepositoryAdapter implements OrderRepository {
    
    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;
    
    /*Inyección de dependencias: Spring le pasa automáticamente un OrderJpaRepository y un OrderMapper.
    De esta forma no se crean con new, sino que Spring los gestiona.*/
    @Autowired
    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, OrderMapper orderMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderMapper = orderMapper;
    }
    
    @Override
    //Convierte un objeto Order del dominio a OrderEntity de BD, lo 
    //guarda con JPA y lo convierte de vuelta al dominio.
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        OrderEntity savedEntity = orderJpaRepository.save(orderEntity);
        return orderMapper.toDomain(savedEntity);
    }
    
    @Override
    /*Busca la orden por su número en la BD.
    Si existe, la convierte a dominio. Si no, devuelve vacío (Optional.empty())*/
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderJpaRepository.findById(orderNumber)
                .map(orderMapper::toDomain);
    }
    
    @Override
   // Recupera todas las órdenes asociadas a un paciente y las convierte de entidad BD a dominio.
    public List<Order> findByPatientId(String patientId) {
        return orderJpaRepository.findByPatientId(patientId)
                .stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    //Igual que el anterior, pero filtrando por el médico.  
    public List<Order> findByDoctorId(String doctorId) {
        return orderJpaRepository.findByDoctorId(doctorId)
                .stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    //Verificar si una orden existw. Devuelve true si la orden existe en la BD, false si no
    public boolean existsByOrderNumber(String orderNumber) {
        return orderJpaRepository.existsById(orderNumber);
    }
}