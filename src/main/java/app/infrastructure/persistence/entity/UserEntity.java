package app.infrastructure.persistence.entity;

import app.domain.model.valueObjects.Role;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @Column(name = "id", length = 20, nullable = false)
    private String id;
    
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Column(name = "address", length = 30)
    private String address;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    
    public UserEntity() {}
    
    public UserEntity(String id, String fullName, String email, String password, String phoneNumber, 
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
