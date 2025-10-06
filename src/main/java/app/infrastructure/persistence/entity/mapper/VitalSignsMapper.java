package app.infrastructure.persistence.entity.mapper;


import app.domain.model.VitalSigns;
import app.infrastructure.persistence.entity.VitalSignsEntity;
import org.springframework.stereotype.Component;

@Component
public class VitalSignsMapper {
    
    public VitalSignsEntity toEntity(VitalSigns vitalSigns) {
        if (vitalSigns == null) return null;
        return new VitalSignsEntity(
            vitalSigns.getId(),
            vitalSigns.getPatientId(),
            vitalSigns.getNurseId(),
            vitalSigns.getRecordDate(),
            vitalSigns.getBloodPressure(),
            vitalSigns.getTemperature(),
            vitalSigns.getPulse(),
            vitalSigns.getOxygenLevel()
        );
    }
    
    public VitalSigns toDomain(VitalSignsEntity entity) {
        if (entity == null) return null;
        return new VitalSigns(
            entity.getId(),
            entity.getPatientId(),
            entity.getNurseId(),
            entity.getRecordDate(),
            entity.getBloodPressure(),
            entity.getTemperature(),
            entity.getPulse(),
            entity.getOxygenLevel()
        );
    }
}
