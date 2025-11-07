package app.infrastructure.persistence.entity.adapter;

import app.domain.model.VitalSigns;
import app.domain.repository.VitalSignsRepository;
import app.infrastructure.persistence.entity.VitalSignsEntity;
import app.infrastructure.persistence.entity.jpa.VitalSignsJpaRepository;
import app.infrastructure.persistence.entity.mapper.VitalSignsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VitalSignsRepositoryAdapter implements VitalSignsRepository {
    
    private final VitalSignsJpaRepository vitalSignsJpaRepository;
    private final VitalSignsMapper vitalSignsMapper;
    
    @Autowired
    public VitalSignsRepositoryAdapter(VitalSignsJpaRepository vitalSignsJpaRepository,
                                      VitalSignsMapper vitalSignsMapper) {
        this.vitalSignsJpaRepository = vitalSignsJpaRepository;
        this.vitalSignsMapper = vitalSignsMapper;
    }
    
    @Override
    public VitalSigns save(VitalSigns vitalSigns) {
        VitalSignsEntity entity = vitalSignsMapper.toEntity(vitalSigns);
        VitalSignsEntity savedEntity = vitalSignsJpaRepository.save(entity);
        return vitalSignsMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<VitalSigns> findById(String id) {
        return vitalSignsJpaRepository.findById(id)
                .map(vitalSignsMapper::toDomain);
    }
    
    @Override
    public List<VitalSigns> findByPatientId(String patientId) {
        return vitalSignsJpaRepository.findByPatientId(patientId)
                .stream()
                .map(vitalSignsMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<VitalSigns> findByNurseId(String nurseId) {
        return vitalSignsJpaRepository.findByNurseId(nurseId)
                .stream()
                .map(vitalSignsMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<VitalSigns> findAll() {
        return vitalSignsJpaRepository.findAll()
                .stream()
                .map(vitalSignsMapper::toDomain)
                .collect(Collectors.toList());
    }
}
