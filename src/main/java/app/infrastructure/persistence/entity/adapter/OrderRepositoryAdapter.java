package app.infrastructure.persistence.entity.adapter;

import app.domain.model.Order;
import app.domain.repository.OrderRepository;
import app.infrastructure.persistence.entity.OrderEntity;
import app.infrastructure.persistence.entity.jpa.OrderJpaRepository;
import app.infrastructure.persistence.entity.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {
    
    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;
    
    @Autowired
    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, 
                                 OrderMapper orderMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderMapper = orderMapper;
    }
    
    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        OrderEntity savedEntity = orderJpaRepository.save(orderEntity);
        return orderMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderJpaRepository.findById(orderNumber)
                .map(orderMapper::toDomain);
    }
    
    @Override
    public List<Order> findByPatientId(String patientId) {
        return orderJpaRepository.findByPatientId(patientId)
                .stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findByDoctorId(String doctorId) {
        return orderJpaRepository.findByDoctorId(doctorId)
                .stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsByOrderNumber(String orderNumber) {
        return orderJpaRepository.existsById(orderNumber);
    }
}