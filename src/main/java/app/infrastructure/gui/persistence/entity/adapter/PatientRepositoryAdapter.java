package app.infrastructure.gui.persistence.entity.adapter;

import app.domain.model.Patient;                    
import app.domain.repository.PatientRepository;      
import app.infrastructure.gui.persistence.entity.PatientEntity;   
import app.infrastructure.gui.persistence.entity.jpa.PatientJpaRepository; 
import app.infrastructure.gui.persistence.entity.mapper.PatientMapper;     
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;              
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que conecta el dominio con la base de datos real.
 * Implementa la interfaz PatientRepository usando Spring Data JPA.
 * Convierte entre objetos de dominio (Patient) y entidades JPA (PatientEntity).
 */
@Repository
public class PatientRepositoryAdapter implements PatientRepository {
    
    // Dependencias necesarias para interactuar con la BD
    private final PatientJpaRepository patientJpaRepository; // Acceso directo a la BD vía JPA
    private final PatientMapper patientMapper;               // Conversión entre dominio y BD
    
    /**
     * Constructor con inyección de dependencias.
     * Spring automáticamente provee una instancia de PatientJpaRepository y PatientMapper.
     */
    @Autowired
    public PatientRepositoryAdapter(PatientJpaRepository patientJpaRepository, 
                                   PatientMapper patientMapper) {
        this.patientJpaRepository = patientJpaRepository;
        this.patientMapper = patientMapper;
    }
    
    /**
     * Guarda un paciente en la base de datos.
     * 1. Convierte el objeto de dominio (Patient) en entidad JPA (PatientEntity).
     * 2. Usa JPA para guardarlo en la tabla "patients".
     * 3. Convierte la entidad guardada de nuevo en objeto de dominio.
     */
    @Override
    public Patient save(Patient patient) {
        PatientEntity patientEntity = patientMapper.toEntity(patient);
        PatientEntity savedEntity = patientJpaRepository.save(patientEntity);
        return patientMapper.toDomain(savedEntity);
    }
    
    /**
     * Busca un paciente en la base de datos por su ID (cédula).
     * Si existe, devuelve el objeto de dominio (Patient).
     * Si no existe, devuelve un Optional vacío.
     */
    @Override
    public Optional<Patient> findById(String id) {
        return patientJpaRepository.findById(id)
                .map(patientMapper::toDomain);
    }
    
    /**
     * Obtiene todos los pacientes registrados en la base de datos.
     * Devuelve una lista de objetos de dominio (Patient).
     */
    @Override
    public List<Patient> findAll() {
        return patientJpaRepository.findAll()
                .stream()
                .map(patientMapper::toDomain)  // Convertimos cada entidad JPA a dominio
                .collect(Collectors.toList());
    }
    
    /**
     * Busca un paciente por su nombre de usuario (username).
     * Retorna un Optional con el paciente si existe.
     */
    @Override
    public Optional<Patient> findByUsername(String username) {
        return patientJpaRepository.findByUsername(username)
                .map(patientMapper::toDomain);
    }
    
    /**
     * Elimina un paciente de la base de datos usando su ID.
     */
    @Override
    public void deleteById(String id) {
        patientJpaRepository.deleteById(id);
    }
    
    /**
     * Verifica si un paciente existe en la base de datos por su ID.
     * Devuelve true si existe, false si no.
     */
    @Override
    public boolean existsById(String id) {
        return patientJpaRepository.existsById(id);
    }
    
    /**
     * Verifica si un paciente existe en la base de datos por su nombre de usuario.
     * Devuelve true si existe, false si no.
     */
    @Override
    public boolean existsByUsername(String username) {
        return patientJpaRepository.existsByUsername(username);
    }
}
