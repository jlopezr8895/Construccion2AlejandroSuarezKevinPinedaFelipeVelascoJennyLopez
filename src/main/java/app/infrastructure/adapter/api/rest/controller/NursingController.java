package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.NursingUseCase;
import app.domain.model.VitalSigns;
import app.infrastructure.adapter.api.rest.dto.nursing.CreateVitalSignsRequest;
import app.infrastructure.adapter.api.rest.dto.nursing.VitalSignsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@org.springframework.security.access.prepost.PreAuthorize("hasRole('NURSE')")
@RequestMapping("/api/nursing/vital-signs")
public class NursingController {

    private final NursingUseCase nursingUseCase;

    public NursingController(NursingUseCase nursingUseCase) {
        this.nursingUseCase = nursingUseCase;
    }

    @PostMapping
    public ResponseEntity<VitalSignsResponse> recordVitalSigns(@RequestBody CreateVitalSignsRequest request) {
        VitalSigns vitalSigns = new VitalSigns(
            null,
            request.getPatientId(),
            request.getNurseId(),
            LocalDateTime.now(),
            request.getBloodPressure(),
            request.getTemperature(),
            request.getPulse(),
            request.getOxygenLevel()
        );

        VitalSigns recorded = nursingUseCase.recordVitalSigns(vitalSigns);
        VitalSignsResponse response = mapToVitalSignsResponse(recorded);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<VitalSignsResponse>> getPatientVitalSigns(@PathVariable String patientId) {
        List<VitalSigns> vitalSignsList = nursingUseCase.getPatientVitalSigns(patientId);
        List<VitalSignsResponse> response = vitalSignsList.stream()
            .map(this::mapToVitalSignsResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/nurse/{nurseId}")
    public ResponseEntity<List<VitalSignsResponse>> getVitalSignsByNurse(@PathVariable String nurseId) {
        List<VitalSigns> vitalSignsList = nursingUseCase.getVitalSignsByNurse(nurseId);
        List<VitalSignsResponse> response = vitalSignsList.stream()
            .map(this::mapToVitalSignsResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignsResponse> getVitalSignsById(@PathVariable String id) {
        VitalSigns vitalSigns = nursingUseCase.findVitalSignsById(id);

        if (vitalSigns == null) {
            return ResponseEntity.notFound().build();
        }

        VitalSignsResponse response = mapToVitalSignsResponse(vitalSigns);
        return ResponseEntity.ok(response);
    }

    private VitalSignsResponse mapToVitalSignsResponse(VitalSigns vitalSigns) {
        VitalSignsResponse response = new VitalSignsResponse();
        response.setId(vitalSigns.getId());
        response.setPatientId(vitalSigns.getPatientId());
        response.setNurseId(vitalSigns.getNurseId());
        response.setRecordDate(vitalSigns.getRecordDate());
        response.setBloodPressure(vitalSigns.getBloodPressure());
        response.setTemperature(vitalSigns.getTemperature());
        response.setPulse(vitalSigns.getPulse());
        response.setOxygenLevel(vitalSigns.getOxygenLevel());
        return response;
    }
}
