
package app.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class OrderEntity {
    
    @Id
    @Column(name = "order_number", length = 6, nullable = false)
    private String orderNumber;
    
    @Column(name = "patient_id", length = 20, nullable = false)
    private String patientId;
    
    @Column(name = "doctor_id", length = 20, nullable = false)
    private String doctorId;
    
    @Column(name = "creation_date")
    private LocalDate creationDate;
    
    @Column(name = "order_type", length = 20)
    private String orderType;
    
    public OrderEntity() {}
    
    public OrderEntity(String orderNumber, String patientId, String doctorId, 
                       LocalDate creationDate, String orderType) {
        this.orderNumber = orderNumber;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.creationDate = creationDate;
        this.orderType = orderType;
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