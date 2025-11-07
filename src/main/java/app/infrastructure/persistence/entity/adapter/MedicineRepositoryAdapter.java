package app.infrastructure.persistence.entity.adapter;

import app.domain.model.Medicine;
import app.domain.repository.MedicineRepository;
import app.infrastructure.persistence.entity.MedicineEntity;
import app.infrastructure.persistence.entity.jpa.MedicineJpaRepository;
import app.infrastructure.persistence.entity.mapper.MedicineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MedicineRepositoryAdapter implements MedicineRepository {
    
    private final MedicineJpaRepository medicineJpaRepository;
    private final MedicineMapper medicineMapper;
    
    @Autowired
    public MedicineRepositoryAdapter(MedicineJpaRepository medicineJpaRepository,
                                    MedicineMapper medicineMapper) {
        this.medicineJpaRepository = medicineJpaRepository;
        this.medicineMapper = medicineMapper;
    }
    
    @Override
    public Medicine save(Medicine medicine) {
        MedicineEntity entity = medicineMapper.toEntity(medicine);
        MedicineEntity savedEntity = medicineJpaRepository.save(entity);
        return medicineMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Medicine> findById(String id) {
        return medicineJpaRepository.findById(id)
                .map(medicineMapper::toDomain);
    }
    
    @Override
    public List<Medicine> findAll() {
        return medicineJpaRepository.findAll()
                .stream()
                .map(medicineMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Medicine> findByNameContaining(String name) {
        return medicineJpaRepository.findByNameContaining(name)
                .stream()
                .map(medicineMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsById(String id) {
        return medicineJpaRepository.existsById(id);
    }
    
    @Override
    public void deleteById(String id) {
        medicineJpaRepository.deleteById(id);
    }
}
