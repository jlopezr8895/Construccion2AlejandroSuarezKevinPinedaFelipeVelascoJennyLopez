package app.infrastructure.persistence.entity.mapper;

import app.domain.model.Patient;
import app.domain.model.EmergencyContact;
import app.domain.model.Insurance;
import app.infrastructure.persistence.entity.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    
    public PatientEntity toEntity(Patient patient) {
        if (patient == null) return null;
        
        PatientEntity entity = new PatientEntity(
            patient.getId(),
            patient.getUsername(),
            patient.getPassword(),
            patient.getFullName(),
            patient.getBirthDate(),
            patient.getGender(),
            patient.getAddress(),
            patient.getPhoneNumber(),
            patient.getEmail()
        );
        
        if (patient.getEmergencyContact() != null) {
            entity.setEmergencyContactName(patient.getEmergencyContact().getName());
            entity.setEmergencyContactRelation(patient.getEmergencyContact().getRelation());
            entity.setEmergencyContactPhone(patient.getEmergencyContact().getPhoneNumber());
        }
        
        if (patient.getInsurance() != null) {
            entity.setInsuranceCompany(patient.getInsurance().getCompanyName());
            entity.setPolicyNumber(patient.getInsurance().getPolicyNumber());
            entity.setIsPolicyActive(patient.getInsurance().isActive());
            entity.setPolicyExpiryDate(patient.getInsurance().getExpiryDate());
        }
        
        return entity;
    }
    
    public Patient toDomain(PatientEntity patientEntity) {
        if (patientEntity == null) return null;
        
        Patient patient = new Patient(
            patientEntity.getId(),
            patientEntity.getUsername(),
            patientEntity.getPassword(),
            patientEntity.getFullName(),
            patientEntity.getBirthDate(),
            patientEntity.getGender(),
            patientEntity.getAddress(),
            patientEntity.getPhoneNumber(),
            patientEntity.getEmail()
        );
        
        if (patientEntity.getEmergencyContactName() != null) {
            EmergencyContact emergencyContact = new EmergencyContact(
                patientEntity.getEmergencyContactName(),
                patientEntity.getEmergencyContactRelation(),
                patientEntity.getEmergencyContactPhone()
            );
            patient.setEmergencyContact(emergencyContact);
        }
        
        if (patientEntity.getInsuranceCompany() != null) {
            Insurance insurance = new Insurance(
                patientEntity.getInsuranceCompany(),
                patientEntity.getPolicyNumber(),
                patientEntity.getIsPolicyActive() != null ? patientEntity.getIsPolicyActive() : false,
                patientEntity.getPolicyExpiryDate()
            );
            patient.setInsurance(insurance);
        }
        
        return patient;
    }
}