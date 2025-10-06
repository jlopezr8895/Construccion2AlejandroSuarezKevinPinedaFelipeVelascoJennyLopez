package app.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "procedure_orders")
public class ProcedureOrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_number", length = 6, nullable = false)
    private String orderNumber;
    
    @Column(name = "item_number", nullable = false)
    private Integer itemNumber;
    
    @Column(name = "procedure_name", length = 100)
    private String procedureName;
    
    @Column(name = "repetitions")
    private Integer repetitions;
    
    @Column(name = "frequency", length = 50)
    private String frequency;
    
    @Column(name = "cost")
    private Double cost;
    
    @Column(name = "requires_specialist")
    private Boolean requiresSpecialist;
    
    @Column(name = "specialist_type_id", length = 20)
    private String specialistTypeId;
    
    public ProcedureOrderEntity() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    
    public Integer getItemNumber() { return itemNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
    
    public String getProcedureName() { return procedureName; }
    public void setProcedureName(String procedureName) { this.procedureName = procedureName; }
    
    public Integer getRepetitions() { return repetitions; }
    public void setRepetitions(Integer repetitions) { this.repetitions = repetitions; }
    
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    
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