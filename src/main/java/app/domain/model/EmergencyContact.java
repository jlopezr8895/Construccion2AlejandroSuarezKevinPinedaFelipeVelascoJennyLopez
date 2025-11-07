
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
    
    public boolean isValid() {
        return name != null && !name.isEmpty() &&
               relation != null && !relation.isEmpty() &&
               phoneNumber != null && phoneNumber.length() == 10;
    }
    
    public String getName() { return name; }
    public String getRelation() { return relation; }
    public String getPhoneNumber() { return phoneNumber; }
}