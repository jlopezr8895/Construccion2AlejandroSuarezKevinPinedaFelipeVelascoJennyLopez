package app.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vital_signs")
public class VitalSignsEntity {
    
    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;
    
    @Column(name = "patient_id", length = 20, nullable = false)
    private String patientId;
    
    @Column(name = "nurse_id", length = 20, nullable = false)
    private String nurseId;
    
    @Column(name = "record_date")
    private LocalDateTime recordDate;
    
    @Column(name = "blood_pressure", length = 20)
    private String bloodPressure;
    
    @Column(name = "temperature")
    private Double temperature;
    
    @Column(name = "pulse")
    private Integer pulse;
    
    @Column(name = "oxygen_level")
    private Integer oxygenLevel;
    
    public VitalSignsEntity() {}
    
    public VitalSignsEntity(String id, String patientId, String nurseId, 
                           LocalDateTime recordDate, String bloodPressure, 
                           Double temperature, Integer pulse, Integer oxygenLevel) {
        this.id = id;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.recordDate = recordDate;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.pulse = pulse;
        this.oxygenLevel = oxygenLevel;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public String getNurseId() { return nurseId; }
    public void setNurseId(String nurseId) { this.nurseId = nurseId; }
    
    public LocalDateTime getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDateTime recordDate) { this.recordDate = recordDate; }
    
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    
    public Integer getPulse() { return pulse; }
    public void setPulse(Integer pulse) { this.pulse = pulse; }
    
    public Integer getOxygenLevel() { return oxygenLevel; }
    public void setOxygenLevel(Integer oxygenLevel) { this.oxygenLevel = oxygenLevel; }
}
