package app.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnostic_aids")
public class DiagnosticAidEntity {
    
    @Id
    @Column(name = "id", length = 20, nullable = false)
    private String id;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "cost", nullable = false)
    private Double cost;
    
    @Column(name = "requires_specialist", nullable = false)
    private Boolean requiresSpecialist;
    
    public DiagnosticAidEntity() {}
    
    public DiagnosticAidEntity(String id, String name, String description, 
                              Double cost, Boolean requiresSpecialist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.requiresSpecialist = requiresSpecialist;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    
    public Boolean getRequiresSpecialist() { return requiresSpecialist; }
    public void setRequiresSpecialist(Boolean requiresSpecialist) { 
        this.requiresSpecialist = requiresSpecialist; 
    }
}