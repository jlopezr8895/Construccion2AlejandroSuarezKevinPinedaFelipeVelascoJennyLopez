package app.application.port.usecase;

import app.application.port.in.NursingUseCase;
import app.domain.model.VitalSigns;
import app.domain.repository.VitalSignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NursingService implements NursingUseCase {
    
    private final VitalSignsRepository vitalSignsRepository;
    
    @Autowired
    public NursingService(VitalSignsRepository vitalSignsRepository) {
        this.vitalSignsRepository = vitalSignsRepository;
    }
    
    @Override
    public VitalSigns recordVitalSigns(VitalSigns vitalSigns) {
        // Generar ID si no tiene
        if (vitalSigns.getId() == null || vitalSigns.getId().isEmpty()) {
            vitalSigns.setId(UUID.randomUUID().toString());
        }
        
        // Establecer fecha/hora actual si no tiene
        if (vitalSigns.getRecordDate() == null) {
            vitalSigns.setRecordDate(LocalDateTime.now());
        }
        
        // Validaciones básicas
        if (vitalSigns.getPatientId() == null || vitalSigns.getPatientId().isEmpty()) {
            throw new IllegalArgumentException("El ID del paciente es requerido");
        }
        
        if (vitalSigns.getNurseId() == null || vitalSigns.getNurseId().isEmpty()) {
            throw new IllegalArgumentException("El ID de la enfermera es requerido");
        }
        
        return vitalSignsRepository.save(vitalSigns);
    }
    
    @Override
    public List<VitalSigns> getPatientVitalSigns(String patientId) {
        return vitalSignsRepository.findByPatientId(patientId);
    }
    
    @Override
    public List<VitalSigns> getVitalSignsByNurse(String nurseId) {
        return vitalSignsRepository.findByNurseId(nurseId);
    }
    
    @Override
    public VitalSigns findVitalSignsById(String id) {
        return vitalSignsRepository.findById(id).orElse(null);
    }
}