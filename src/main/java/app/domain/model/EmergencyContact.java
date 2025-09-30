
package app.domain.model;


public class EmergencyContact {
    private final String name;
    private final String relation;
    private final String phoneNumber;
    
    public EmergencyContact(String name, String relation, String phoneNumber) {
        this.name = name;
        this.relation = relation;
        this.phoneNumber = phoneNumber;
    }
    
/* Verifica que el nombre no sea nulo ni vacío,
que la relación también esté presente y
que el teléfono tenga exactamente 10 dígitos.*/
    public boolean isValid() {
        return name != null && !name.isEmpty() &&
               relation != null && !relation.isEmpty() &&
               phoneNumber != null && phoneNumber.length() == 10;
    }
    
    public String getName() { return name; }
    public String getRelation() { return relation; }
    public String getPhoneNumber() { return phoneNumber; }
}