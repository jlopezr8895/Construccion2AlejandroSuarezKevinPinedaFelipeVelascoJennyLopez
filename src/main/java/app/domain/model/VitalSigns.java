
package app.domain.model;


import java.time.LocalDateTime;

public class VitalSigns {
    private String id;
    private String patientId;
    private String nurseId;
    private LocalDateTime recordDate;
    private String bloodPressure;
    private double temperature;
    private int pulse;
    private int oxygenLevel;
    
    public VitalSigns() {}
    
    public VitalSigns(String id, String patientId, String nurseId, 
                      LocalDateTime recordDate, String bloodPressure, 
                      double temperature, int pulse, int oxygenLevel) {
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
    
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    public int getPulse() { return pulse; }
    public void setPulse(int pulse) { this.pulse = pulse; }
    
    public int getOxygenLevel() { return oxygenLevel; }
    public void setOxygenLevel(int oxygenLevel) { this.oxygenLevel = oxygenLevel; }
}

