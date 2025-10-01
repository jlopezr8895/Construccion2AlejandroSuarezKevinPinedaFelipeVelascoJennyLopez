
package app.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medicine_orders")
public class MedicineOrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_number", length = 6, nullable = false)
    private String orderNumber;
    
    @Column(name = "item_number", nullable = false)
    private Integer itemNumber;
    
    @Column(name = "medicine_name", length = 100)
    private String medicineName;
    
    @Column(name = "dosage", length = 50)
    private String dosage;
    
    @Column(name = "treatment_duration", length = 50)
    private String treatmentDuration;
    
    @Column(name = "cost")
    private Double cost;
    
    public MedicineOrderEntity() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    
    public Integer getItemNumber() { return itemNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
    
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    
    public String getTreatmentDuration() { return treatmentDuration; }
    public void setTreatmentDuration(String treatmentDuration) { 
        this.treatmentDuration = treatmentDuration; 
    }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}