package app.application.port.in;

import app.domain.model.Patient;
import java.util.List;

public interface PatientManagementUseCase {
    Patient registerPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient findPatientById(String patientId);
    Patient findPatientByUsername(String username);
    Patient updatePatient(Patient patient);
    String generateBill(String patientId);
}