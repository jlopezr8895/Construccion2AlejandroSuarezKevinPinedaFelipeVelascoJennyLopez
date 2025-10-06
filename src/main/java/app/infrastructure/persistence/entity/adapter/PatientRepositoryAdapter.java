package app.infrastructure.persistence.entity.adapter;

import app.domain.model.Patient;
import app.domain.repository.PatientRepository;
import app.infrastructure.persistence.entity.PatientEntity;
import app.infrastructure.persistence.entity.jpa.PatientJpaRepository;
import app.infrastructure.persistence.entity.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PatientRepositoryAdapter implements PatientRepository {
    
    private final PatientJpaRepository patientJpaRepository;
    private final PatientMapper patientMapper;
    
    @Autowired
    public PatientRepositoryAdapter(PatientJpaRepository patientJpaRepository, 
                                   PatientMapper patientMapper) {
        this.patientJpaRepository = patientJpaRepository;
        this.patientMapper = patientMapper;
    }
    
    @Override
    public Patient save(Patient patient) {
        PatientEntity patientEntity = patientMapper.toEntity(patient);
        PatientEntity savedEntity = patientJpaRepository.save(patientEntity);
        return patientMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Patient> findById(String id) {
        return patientJpaRepository.findById(id)
                .map(patientMapper::toDomain);
    }
    
    @Override
    public List<Patient> findAll() {
        return patientJpaRepository.findAll()
                .stream()
                .map(patientMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Patient> findByUsername(String username) {
        return patientJpaRepository.findByUsername(username)
                .map(patientMapper::toDomain);
    }
    
    @Override
    public void deleteById(String id) {
        patientJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(String id) {
        return patientJpaRepository.existsById(id);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        return patientJpaRepository.existsByUsername(username);
    }
}