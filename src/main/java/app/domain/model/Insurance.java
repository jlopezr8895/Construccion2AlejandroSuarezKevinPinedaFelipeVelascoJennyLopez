
package app.domain.model;


import java.time.LocalDate;

public class Insurance {
    private final String companyName;
    private final String policyNumber;
    private final boolean isActive;
    private final LocalDate expiryDate;
    
    public Insurance(String companyName, String policyNumber, 
                    boolean isActive, LocalDate expiryDate) {
        this.companyName = companyName;
        this.policyNumber = policyNumber;
        this.isActive = isActive;
        this.expiryDate = expiryDate;
    }
    
    public boolean isValid() {
        return companyName != null && !companyName.isEmpty() &&
               policyNumber != null && !policyNumber.isEmpty();
    }
    
    public boolean isCurrentlyActive() {
        if (!isActive) return false;
        if (expiryDate == null) return false;
        return LocalDate.now().isBefore(expiryDate) || 
               LocalDate.now().isEqual(expiryDate);
    }
    
    public String getCompanyName() { return companyName; }
    public String getPolicyNumber() { return policyNumber; }
    public boolean isActive() { return isActive; }
    public LocalDate getExpiryDate() { return expiryDate; }
}