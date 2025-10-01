
package app.domain.model;


import java.time.LocalDate;

public class MedicalRecord {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDate date;
    private String reason;
    private String symptoms;
    private String diagnosis;
    
    public MedicalRecord() {}
    
    public MedicalRecord(String id, String patientId, String doctorId, 
                         LocalDate date, String reason, String symptoms, String diagnosis) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
    }
    
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
