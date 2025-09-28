
package app.domain.model;

import java.time.LocalDate;

public class Order {
    private String orderNumber;
    private String patientId;
    private String doctorId;
    private LocalDate creationDate;
    private String orderType;
    
    public Order() {}
    
    public Order(String orderNumber, String patientId, String doctorId, 
                 LocalDate creationDate, String orderType) {
        this.orderNumber = orderNumber;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.creationDate = creationDate;
        this.orderType = orderType;
    }
    
    public boolean isValid() {
        return orderNumber != null && orderNumber.length() <= 6 &&
               patientId != null && !patientId.isEmpty() &&
               doctorId != null && !doctorId.isEmpty();
    }
    
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    
    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
}
