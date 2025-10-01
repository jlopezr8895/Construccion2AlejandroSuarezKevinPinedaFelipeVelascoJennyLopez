
package app.domain.model.valueObjects;

public enum Role {
    HUMAN_RESOURCES("Recursos Humanos"),
    ADMINISTRATIVE("Personal Administrativo"), 
    INFORMATION_SUPPORT("Soporte de Información"),
    NURSE("Enfermera"),
    DOCTOR("Médico");
    
    private final String description;
    
    Role(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}