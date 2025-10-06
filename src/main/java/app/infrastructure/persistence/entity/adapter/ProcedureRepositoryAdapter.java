package app.infrastructure.persistence.entity.adapter;

import app.domain.model.Procedure;
import app.domain.repository.ProcedureRepository;
import app.infrastructure.persistence.entity.ProcedureEntity;
import app.infrastructure.persistence.entity.jpa.ProcedureJpaRepository;
import app.infrastructure.persistence.entity.mapper.ProcedureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProcedureRepositoryAdapter implements ProcedureRepository {
    
    private final ProcedureJpaRepository procedureJpaRepository;
    private final ProcedureMapper procedureMapper;
    
    @Autowired
    public ProcedureRepositoryAdapter(ProcedureJpaRepository procedureJpaRepository,
                                     ProcedureMapper procedureMapper) {
        this.procedureJpaRepository = procedureJpaRepository;
        this.procedureMapper = procedureMapper;
    }
    
    @Override
    public Procedure save(Procedure procedure) {
        ProcedureEntity entity = procedureMapper.toEntity(procedure);
        ProcedureEntity savedEntity = procedureJpaRepository.save(entity);
        return procedureMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Procedure> findById(String id) {
        return procedureJpaRepository.findById(id)
                .map(procedureMapper::toDomain);
    }
    
    @Override
    public List<Procedure> findAll() {
        return procedureJpaRepository.findAll()
                .stream()
                .map(procedureMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Procedure> findByNameContaining(String name) {
        return procedureJpaRepository.findByNameContaining(name)
                .stream()
                .map(procedureMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Procedure> findByRequiresSpecialist(boolean requiresSpecialist) {
        return procedureJpaRepository.findByRequiresSpecialist(requiresSpecialist)
                .stream()
                .map(procedureMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsById(String id) {
        return procedureJpaRepository.existsById(id);
    }
    
    @Override
    public void deleteById(String id) {
        procedureJpaRepository.deleteById(id);
    }
}
