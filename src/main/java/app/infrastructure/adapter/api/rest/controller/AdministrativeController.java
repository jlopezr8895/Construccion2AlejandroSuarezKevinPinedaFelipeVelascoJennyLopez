package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.PatientManagementUseCase;
import app.domain.model.Patient;
import app.infrastructure.adapter.api.rest.dto.patient.BillResponse;
import app.infrastructure.adapter.api.rest.dto.patient.CreatePatientRequest;
import app.infrastructure.adapter.api.rest.dto.patient.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMINISTRATIVE')")
@RequestMapping("/api/patients")
public class AdministrativeController {

    private final PatientManagementUseCase patientManagementUseCase;

    public AdministrativeController(PatientManagementUseCase patientManagementUseCase) {
        this.patientManagementUseCase = patientManagementUseCase;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> registerPatient(@RequestBody CreatePatientRequest request) {
        Patient patient = new Patient(
            request.getId(),
            request.getUsername(),
            request.getPassword(),
            request.getFullName(),
            request.getBirthDate(),
            request.getGender(),
            request.getAddress(),
            request.getPhoneNumber(),
            request.getEmail()
        );

        if (request.getEmergencyContact() != null) {
            patient.setEmergencyContact(request.getEmergencyContact());
        }

        if (request.getInsurance() != null) {
            patient.setInsurance(request.getInsurance());
        }

        Patient createdPatient = patientManagementUseCase.registerPatient(patient);
        PatientResponse response = mapToPatientResponse(createdPatient);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<Patient> patients = patientManagementUseCase.getAllPatients();
        List<PatientResponse> response = patients.stream()
            .map(this::mapToPatientResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable String id) {
        Patient patient = patientManagementUseCase.findPatientById(id);

        if (patient == null) {
            return ResponseEntity.notFound().build();
        }

        PatientResponse response = mapToPatientResponse(patient);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<PatientResponse> getPatientByUsername(@PathVariable String username) {
        Patient patient = patientManagementUseCase.findPatientByUsername(username);

        if (patient == null) {
            return ResponseEntity.notFound().build();
        }

        PatientResponse response = mapToPatientResponse(patient);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable String id,
            @RequestBody CreatePatientRequest request) {
        
        Patient patient = new Patient(
            id,
            request.getUsername(),
            request.getPassword(),
            request.getFullName(),
            request.getBirthDate(),
            request.getGender(),
            request.getAddress(),
            request.getPhoneNumber(),
            request.getEmail()
        );

        if (request.getEmergencyContact() != null) {
            patient.setEmergencyContact(request.getEmergencyContact());
        }

        if (request.getInsurance() != null) {
            patient.setInsurance(request.getInsurance());
        }

        Patient updatedPatient = patientManagementUseCase.updatePatient(patient);
        PatientResponse response = mapToPatientResponse(updatedPatient);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/bill")
    public ResponseEntity<BillResponse> generateBill(@PathVariable String id) {
        String bill = patientManagementUseCase.generateBill(id);
        BillResponse response = new BillResponse(bill);
        return ResponseEntity.ok(response);
    }

    private PatientResponse mapToPatientResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setUsername(patient.getUsername());
        response.setFullName(patient.getFullName());
        response.setBirthDate(patient.getBirthDate());
        response.setGender(patient.getGender());
        response.setAddress(patient.getAddress());
        response.setPhoneNumber(patient.getPhoneNumber());
        response.setEmail(patient.getEmail());
        response.setAge(patient.getAge());
        response.setEmergencyContact(patient.getEmergencyContact());
        response.setInsurance(patient.getInsurance());
        return response;
    }
}
