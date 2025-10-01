
package app.infrastructure.persistence.entity;

import jakarta.persistence.*;
/*Las clases Java anotadas con @Entity funcionan como planos, y Hibernate las 
convierte automáticamente en tablas en la base de datos según la configuración 
de ddl-auto, creando o actualizando las tablas sin que tengamos que escribir SQL manual.*/


/**
 * Entidad JPA que representa la tabla "diagnostic_orders" en la base de datos.
 * Cada instancia de esta clase corresponde a un registro (fila) en esa tabla.
 */
@Entity
@Table(name = "diagnostic_orders")
public class DiagnosticOrderEntity {
    
    /**
     * Clave primaria de la tabla.
     * Se genera automáticamente (auto-incremental en MySQL).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Número de la orden a la que pertenece este ítem.
     * Longitud máxima: 6 caracteres.
     * No puede ser nulo.
     */
    @Column(name = "order_number", length = 6, nullable = false)
    private String orderNumber;
    
    /**
     * Número del ítem dentro de la orden.
     * No puede ser nulo.
     */
    @Column(name = "item_number", nullable = false)
    private Integer itemNumber;
    
    /**
     * Nombre de la ayuda diagnóstica solicitada
     * (ejemplo: "Radiografía de tórax").
     * Longitud máxima: 100 caracteres.
     */
    @Column(name = "diagnostic_aid_name", length = 100)
    private String diagnosticAidName;
    
    /**
     * Cantidad solicitada de este examen.
     */
    @Column(name = "quantity")
    private Integer quantity;
    
    /**
     * Costo del examen o ayuda diagnóstica.
     */
    @Column(name = "cost")
    private Double cost;
    
    /**
     * Indica si se requiere un especialista para este examen.
     * True = sí requiere, False = no requiere.
     */
    @Column(name = "requires_specialist")
    private Boolean requiresSpecialist;
    
    /**
     * Identificador del tipo de especialista requerido
     * (ejemplo: "CARDIOLOGO", "RADIOLOGO").
     * Longitud máxima: 20 caracteres.
     */
    @Column(name = "specialist_type_id", length = 20)
    private String specialistTypeId;
    
    /**
     * Constructor vacío (obligatorio para JPA).
     * Hibernate lo usa para crear instancias automáticamente.
     */
    public DiagnosticOrderEntity() {}
    
    //  Getters y Setters 
    
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
