package app.infrastructure.persistence.entity.mapper;

import app.domain.model.MedicalRecord;
import app.infrastructure.persistence.entity.MedicalRecordEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapper {
    
    public MedicalRecordEntity toEntity(MedicalRecord medicalRecord) {
        if (medicalRecord == null) return null;
        
        return new MedicalRecordEntity(
            medicalRecord.getId(),
            medicalRecord.getPatientId(),
            medicalRecord.getDoctorId(),
            medicalRecord.getDate(),
            medicalRecord.getReason(),
            medicalRecord.getSymptoms(),
            medicalRecord.getDiagnosis()
        );
    }
    
    public MedicalRecord toDomain(MedicalRecordEntity entity) {
        if (entity == null) return null;
        
        return new MedicalRecord(
            entity.getId(),
            entity.getPatientId(),
            entity.getDoctorId(),
            entity.getDate(),
            entity.getReason(),
            entity.getSymptoms(),
            entity.getDiagnosis()
        );
    }
}