package app.domain.model;

import app.domain.model.valueObjects.Role;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users") 
public class User {
    
    @Id
    private String id;

    private String fullName;

    private String email;

    private String password;

    private String phoneNumber;

    private LocalDate birthDate;

    private String address;

    @Enumerated(EnumType.STRING) // para que Role se guarde como texto en la BD
    private Role role;

    public User() {}

    public User(String id, String fullName, String email, String password, String phoneNumber,
                LocalDate birthDate, String address, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.role = role;
    }

    // Constructor de compatibilidad sin password
    public User(String id, String fullName, String email, String phoneNumber,
                LocalDate birthDate, String address, Role role) {
        this(id, fullName, email, null, phoneNumber, birthDate, address, role);
    }

    public boolean isValid() {
        return id != null && !id.isEmpty() &&
               fullName != null && !fullName.isEmpty() &&
               email != null && email.contains("@") &&
               phoneNumber != null && phoneNumber.length() >= 1 && phoneNumber.length() <= 10 &&
               role != null;
    }

    // getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
