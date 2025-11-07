package app.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
public class MedicalRecordEntity {
    
    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;
    
    @Column(name = "patient_id", length = 20, nullable = false)
    private String patientId;
    
    @Column(name = "doctor_id", length = 20, nullable = false)
    private String doctorId;
    
    @Column(name = "date")
    private LocalDate date;
    
    @Column(name = "reason", length = 500)
    private String reason;
    
    @Column(name = "symptoms", length = 1000)
    private String symptoms;
    
    @Column(name = "diagnosis", length = 1000)
    private String diagnosis;
    
    public MedicalRecordEntity() {}
    
    public MedicalRecordEntity(String id, String patientId, String doctorId, 
                              LocalDate date, String reason, String symptoms, String diagnosis) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
}
