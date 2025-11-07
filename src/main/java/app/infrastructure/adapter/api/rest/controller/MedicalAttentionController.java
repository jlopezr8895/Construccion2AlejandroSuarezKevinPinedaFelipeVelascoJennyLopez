package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.MedicalAttentionUseCase;
import app.domain.model.MedicalRecord;
import app.domain.model.Order;
import app.infrastructure.adapter.api.rest.dto.medical.CreateMedicalRecordRequest;
import app.infrastructure.adapter.api.rest.dto.medical.CreateOrderRequest;
import app.infrastructure.adapter.api.rest.dto.medical.MedicalRecordResponse;
import app.infrastructure.adapter.api.rest.dto.medical.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@org.springframework.security.access.prepost.PreAuthorize("hasRole('DOCTOR')")
@RequestMapping("/api/medical")
public class MedicalAttentionController {

    private final MedicalAttentionUseCase medicalAttentionUseCase;

    public MedicalAttentionController(MedicalAttentionUseCase medicalAttentionUseCase) {
        this.medicalAttentionUseCase = medicalAttentionUseCase;
    }

    @PostMapping("/medical-records")
    public ResponseEntity<MedicalRecordResponse> createMedicalRecord(@RequestBody CreateMedicalRecordRequest request) {
        MedicalRecord medicalRecord = new MedicalRecord(
            null,
            request.getPatientId(),
            request.getDoctorId(),
            LocalDate.now(),
            request.getReason(),
            request.getSymptoms(),
            request.getDiagnosis()
        );

        MedicalRecord created = medicalAttentionUseCase.createMedicalRecord(medicalRecord);
        MedicalRecordResponse response = mapToMedicalRecordResponse(created);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/medical-records/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordResponse>> getPatientMedicalHistory(@PathVariable String patientId) {
        List<MedicalRecord> records = medicalAttentionUseCase.getPatientMedicalHistory(patientId);
        List<MedicalRecordResponse> response = records.stream()
            .map(this::mapToMedicalRecordResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createMedicalOrder(@RequestBody CreateOrderRequest request) {
        Order order = new Order(
            request.getOrderNumber(),
            request.getPatientId(),
            request.getDoctorId(),
            LocalDate.now(),
            request.getOrderType()
        );

        Order created = medicalAttentionUseCase.createMedicalOrder(order);
        OrderResponse response = mapToOrderResponse(created);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/orders/patient/{patientId}")
    public ResponseEntity<List<OrderResponse>> getPatientOrders(@PathVariable String patientId) {
        List<Order> orders = medicalAttentionUseCase.getPatientOrders(patientId);
        List<OrderResponse> response = orders.stream()
            .map(this::mapToOrderResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    private MedicalRecordResponse mapToMedicalRecordResponse(MedicalRecord record) {
        MedicalRecordResponse response = new MedicalRecordResponse();
        response.setId(record.getId());
        response.setPatientId(record.getPatientId());
        response.setDoctorId(record.getDoctorId());
        response.setDate(record.getDate());
        response.setReason(record.getReason());
        response.setSymptoms(record.getSymptoms());
        response.setDiagnosis(record.getDiagnosis());
        return response;
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderNumber(order.getOrderNumber());
        response.setPatientId(order.getPatientId());
        response.setDoctorId(order.getDoctorId());
        response.setCreationDate(order.getCreationDate());
        response.setOrderType(order.getOrderType());
        return response;
    }
}
