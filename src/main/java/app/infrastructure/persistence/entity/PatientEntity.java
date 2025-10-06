package app.infrastructure.persistence.entity;

import app.domain.model.valueObjects.Gender;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class PatientEntity {
    
    @Id
    @Column(name = "id", length = 20, nullable = false)
    private String id;
    
    @Column(name = "username", length = 15, nullable = false, unique = true)
    private String username;
    
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    
    @Column(name = "address", length = 100)
    private String address;
    
    @Column(name = "phone_number", length = 10)
    private String phoneNumber;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "emergency_contact_name", length = 100)
    private String emergencyContactName;
    
    @Column(name = "emergency_contact_relation", length = 50)
    private String emergencyContactRelation;
    
    @Column(name = "emergency_contact_phone", length = 10)
    private String emergencyContactPhone;
    
    @Column(name = "insurance_company", length = 100)
    private String insuranceCompany;
    
    @Column(name = "policy_number", length = 50)
    private String policyNumber;
    
    @Column(name = "is_policy_active")
    private Boolean isPolicyActive;
    
    @Column(name = "policy_expiry_date")
    private LocalDate policyExpiryDate;
    
    public PatientEntity() {}
    
    public PatientEntity(String id, String username, String password, String fullName, 
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
    
    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { 
        this.emergencyContactName = emergencyContactName; 
    }
    
    public String getEmergencyContactRelation() { return emergencyContactRelation; }
    public void setEmergencyContactRelation(String emergencyContactRelation) { 
        this.emergencyContactRelation = emergencyContactRelation; 
    }
    
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { 
        this.emergencyContactPhone = emergencyContactPhone; 
    }
    
    public String getInsuranceCompany() { return insuranceCompany; }
    public void setInsuranceCompany(String insuranceCompany) { 
        this.insuranceCompany = insuranceCompany; 
    }
    
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    
    public Boolean getIsPolicyActive() { return isPolicyActive; }
    public void setIsPolicyActive(Boolean isPolicyActive) { this.isPolicyActive = isPolicyActive; }
    
    public LocalDate getPolicyExpiryDate() { return policyExpiryDate; }
    public void setPolicyExpiryDate(LocalDate policyExpiryDate) { 
        this.policyExpiryDate = policyExpiryDate; 
    }
}