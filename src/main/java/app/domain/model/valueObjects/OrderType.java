
package app.domain.model.valueObjects;


public enum OrderType {
    MEDICINE("Medicamento"),
    PROCEDURE("Procedimiento"),
    DIAGNOSTIC_AID("Ayuda Diagnóstica");
    
    private final String description;
    
    OrderType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}