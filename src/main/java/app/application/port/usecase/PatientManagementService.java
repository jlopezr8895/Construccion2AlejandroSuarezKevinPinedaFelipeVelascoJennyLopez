package app.application.port.usecase;

import app.application.port.in.PatientManagementUseCase;
import app.domain.model.Patient;
import app.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PatientManagementService implements PatientManagementUseCase {
    
    private final PatientRepository patientRepository;
    
    @Autowired
    public PatientManagementService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    @Override
    public Patient registerPatient(Patient patient) {
        if (!patient.isValid()) {
            throw new IllegalArgumentException("Los datos del paciente no son válidos");
        }
        if (patientRepository.existsById(patient.getId())) {
            throw new IllegalArgumentException("Ya existe un paciente con la cédula: " + patient.getId());
        }
        if (patientRepository.existsByUsername(patient.getUsername())) {
            throw new IllegalArgumentException("Ya existe un paciente con el username: " + patient.getUsername());
        }
        return patientRepository.save(patient);
    }
    
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    @Override
    public Patient findPatientById(String patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }
    
    @Override
    public Patient findPatientByUsername(String username) {
        return patientRepository.findByUsername(username).orElse(null);
    }
    
    @Override
    public Patient updatePatient(Patient patient) {
        if (!patientRepository.existsById(patient.getId())) {
            throw new IllegalArgumentException("No existe un paciente con la cédula: " + patient.getId());
        }
        if (!patient.isValid()) {
            throw new IllegalArgumentException("Los datos del paciente no son válidos");
        }
        return patientRepository.save(patient);
    }
    
    @Override
    public String generateBill(String patientId) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            return "No se encontró el paciente con cédula: " + patientId;
        }
        
        StringBuilder bill = new StringBuilder();
        bill.append("=== FACTURA CLÍNICA ===\n");
        bill.append("Fecha: ").append(LocalDate.now()).append("\n");
        bill.append("Paciente: ").append(patient.getFullName()).append("\n");
        bill.append("Cédula: ").append(patient.getId()).append("\n");
        bill.append("Edad: ").append(patient.getAge()).append(" años\n");
        
        if (patient.getInsurance() != null) {
            bill.append("Compañía de Seguro: ").append(patient.getInsurance().getCompanyName()).append("\n");
            bill.append("Número de Póliza: ").append(patient.getInsurance().getPolicyNumber()).append("\n");
            
            if (patient.getInsurance().isCurrentlyActive()) {
                long daysToExpiry = ChronoUnit.DAYS.between(LocalDate.now(), patient.getInsurance().getExpiryDate());
                bill.append("Días de Vigencia: ").append(daysToExpiry).append("\n");
                bill.append("Fecha de Finalización: ").append(patient.getInsurance().getExpiryDate()).append("\n");
                bill.append("Copago: $50,000\n");
            } else {
                bill.append("PÓLIZA INACTIVA - Pago total del paciente\n");
            }
        } else {
            bill.append("Sin seguro médico - Pago total del paciente\n");
        }
        
        bill.append("========================\n");
        return bill.toString();
    }
}