package app.infrastructure.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "medicines")
public class MedicineEntity {
    
    @Id
    @Column(name = "id", length = 20, nullable = false)
    private String id;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "cost", nullable = false)
    private Double cost;
    
    public MedicineEntity() {}
    
    public MedicineEntity(String id, String name, String description, Double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}