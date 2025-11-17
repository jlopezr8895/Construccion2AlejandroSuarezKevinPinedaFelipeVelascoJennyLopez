package app.domain.model;

import app.domain.model.valueObjects.Role;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    private String username;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private LocalDate birthDate;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String id, String username, String fullName, String email, String password,
                String phoneNumber, LocalDate birthDate, String address, Role role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.role = role;
    }

    // Constructor alternativo sin password (evitar uso en producción)
    public User(String id, String username, String fullName, String email,
                String phoneNumber, LocalDate birthDate, String address, Role role) {
        this(id, username, fullName, email, null, phoneNumber, birthDate, address, role);
    }

    // Validación del usuario antes de crearlo
    public boolean isValid() {
        return id != null && !id.isEmpty() &&
               username != null && !username.isEmpty() &&
               fullName != null && !fullName.isEmpty() &&
               email != null && email.contains("@") &&
               password != null && password.length() >= 8 &&
               phoneNumber != null && phoneNumber.length() == 10 &&
               role != null;
    }

    // Getters y setters
    public String getId() { 
        return id; 
    }
    public void setId(String id) { 
        this.id = id; 
    }

    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }

    public String getFullName() { 
        return fullName; 
    }
    public void setFullName(String fullName) { 
        this.fullName = fullName; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getPhoneNumber() { 
        return phoneNumber; 
    }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }

    public LocalDate getBirthDate() { 
        return birthDate; 
    }
    public void setBirthDate(LocalDate birthDate) { 
        this.birthDate = birthDate; 
    }

    public String getAddress() { 
        return address; 
    }
    public void setAddress(String address) { 
        this.address = address; 
    }

    public Role getRole() { 
        return role; 
    }
    public void setRole(Role role) { 
        this.role = role; 
    }
}
