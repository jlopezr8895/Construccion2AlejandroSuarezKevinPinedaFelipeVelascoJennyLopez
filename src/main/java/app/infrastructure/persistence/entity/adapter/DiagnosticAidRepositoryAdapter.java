package app.infrastructure.persistence.entity.adapter;

import app.domain.model.DiagnosticAid;
import app.domain.repository.DiagnosticAidRepository;
import app.infrastructure.persistence.entity.DiagnosticAidEntity;
import app.infrastructure.persistence.entity.jpa.DiagnosticAidJpaRepository;
import app.infrastructure.persistence.entity.mapper.DiagnosticAidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DiagnosticAidRepositoryAdapter implements DiagnosticAidRepository {
    
    private final DiagnosticAidJpaRepository diagnosticAidJpaRepository;
    private final DiagnosticAidMapper diagnosticAidMapper;
    
    @Autowired
    public DiagnosticAidRepositoryAdapter(DiagnosticAidJpaRepository diagnosticAidJpaRepository,
                                         DiagnosticAidMapper diagnosticAidMapper) {
        this.diagnosticAidJpaRepository = diagnosticAidJpaRepository;
        this.diagnosticAidMapper = diagnosticAidMapper;
    }
    
    @Override
    public DiagnosticAid save(DiagnosticAid diagnosticAid) {
        DiagnosticAidEntity entity = diagnosticAidMapper.toEntity(diagnosticAid);
        DiagnosticAidEntity savedEntity = diagnosticAidJpaRepository.save(entity);
        return diagnosticAidMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<DiagnosticAid> findById(String id) {
        return diagnosticAidJpaRepository.findById(id)
                .map(diagnosticAidMapper::toDomain);
    }
    
    @Override
    public List<DiagnosticAid> findAll() {
        return diagnosticAidJpaRepository.findAll()
                .stream()
                .map(diagnosticAidMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DiagnosticAid> findByNameContaining(String name) {
        return diagnosticAidJpaRepository.findByNameContaining(name)
                .stream()
                .map(diagnosticAidMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DiagnosticAid> findByRequiresSpecialist(boolean requiresSpecialist) {
        return diagnosticAidJpaRepository.findByRequiresSpecialist(requiresSpecialist)
                .stream()
                .map(diagnosticAidMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsById(String id) {
        return diagnosticAidJpaRepository.existsById(id);
    }
    
    @Override
    public void deleteById(String id) {
        diagnosticAidJpaRepository.deleteById(id);
    }
}
