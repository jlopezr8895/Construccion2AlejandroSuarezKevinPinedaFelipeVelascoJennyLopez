
package app.domain.model.valueObjects;

/*Cada opción tiene una descripción en texto, accesible mediante el método getDescription().
Esto evita que se ingresen valores incorrectos, ya que el género siempre debe ser uno de los tres definidos en la enumeración.*/
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
