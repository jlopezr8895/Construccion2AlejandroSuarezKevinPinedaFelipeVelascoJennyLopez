
package app.domain.model;


import app.domain.model.valueObjects.Gender;
import java.time.LocalDate;

public class Patient {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private String email;
    private EmergencyContact emergencyContact;
    private Insurance insurance;
    
    public Patient() {}
    
    public Patient(String id, String username, String password, String fullName, 
                   LocalDate birthDate, Gender gender, String address, 
                   String phoneNumber, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public boolean isValid() {
        return id != null && !id.isEmpty() &&
               username != null && username.length() <= 15 &&
               password != null && isValidPassword(password) &&
               fullName != null && !fullName.isEmpty() &&
               phoneNumber != null && phoneNumber.length() == 10;
    }
    
    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasNumber = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasNumber && hasSpecial;
    }
    
    public int getAge() {
        if (birthDate == null) return 0;
        return LocalDate.now().getYear() - birthDate.getYear();
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public EmergencyContact getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(EmergencyContact emergencyContact) { 
        this.emergencyContact = emergencyContact; 
    }
    
    public Insurance getInsurance() { return insurance; }
    public void setInsurance(Insurance insurance) { 
        this.insurance = insurance; 
    }
}