
package app.domain.model.valueObjects;


public enum Gender {
    MALE("Masculino"),
    FEMALE("Femenino"),
    OTHER("Otro");
    
    private final String description;
    
    Gender(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
