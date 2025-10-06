
package app.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnostic_orders")
public class DiagnosticOrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_number", length = 6, nullable = false)
    private String orderNumber;
    
    @Column(name = "item_number", nullable = false)
    private Integer itemNumber;
    
    @Column(name = "diagnostic_aid_name", length = 100)
    private String diagnosticAidName;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "cost")
    private Double cost;
    
    @Column(name = "requires_specialist")
    private Boolean requiresSpecialist;
    
    @Column(name = "specialist_type_id", length = 20)
    private String specialistTypeId;
    
    public DiagnosticOrderEntity() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    
    public Integer getItemNumber() { return itemNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
    
    public String getDiagnosticAidName() { return diagnosticAidName; }
    public void setDiagnosticAidName(String diagnosticAidName) { 
        this.diagnosticAidName = diagnosticAidName; 
    }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    
    public Boolean getRequiresSpecialist() { return requiresSpecialist; }
    public void setRequiresSpecialist(Boolean requiresSpecialist) { 
        this.requiresSpecialist = requiresSpecialist; 
    }
    
    public String getSpecialistTypeId() { return specialistTypeId; }
    public void setSpecialistTypeId(String specialistTypeId) { 
        this.specialistTypeId = specialistTypeId; 
    }
}