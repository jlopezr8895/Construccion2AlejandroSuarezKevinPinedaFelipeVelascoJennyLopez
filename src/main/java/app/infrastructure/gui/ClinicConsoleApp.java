package app.infrastructure.gui;

import app.application.port.in.UserManagementUseCase;
import app.application.port.in.PatientManagementUseCase;
import app.domain.model.User;
import app.domain.model.Patient;
import app.domain.model.EmergencyContact;
import app.domain.model.Insurance;
import app.domain.model.valueObjects.Role;
import app.domain.model.valueObjects.Gender;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicConsoleApp {
    
    private final UserManagementUseCase userManagementUseCase;
    private final PatientManagementUseCase patientManagementUseCase;
    private final Scanner scanner;
    
    @Autowired
    public ClinicConsoleApp(UserManagementUseCase userManagementUseCase, 
                           PatientManagementUseCase patientManagementUseCase) {
        this.userManagementUseCase = userManagementUseCase;
        this.patientManagementUseCase = patientManagementUseCase;
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("=================================");
        System.out.println("    SISTEMA DE GESTIÓN CLÍNICA   ");
        System.out.println("=================================");
        
        while (true) {
            showMainMenu();
            int option = readInt("Seleccione una opción: ");
            
            switch (option) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    patientManagementMenu();
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el sistema!");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Usuarios (Recursos Humanos)");
        System.out.println("2. Gestión de Pacientes (Personal Administrativo)");
        System.out.println("3. Salir");
    }
    
    private void userManagementMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE USUARIOS ===");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Buscar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Volver al Menú Principal");
            
            int option = readInt("Seleccione una opción: ");
            
            switch (option) {
                case 1:
                    createUser();
                    break;
                case 2:
                    listUsers();
                    break;
                case 3:
                    searchUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void createUser() {
        System.out.println("\n=== CREAR USUARIO ===");
        
        try {
            String id = readString("Cédula: ");
            String fullName = readString("Nombre completo: ");
            String email = readString("Email: ");
            String phoneNumber = readString("Teléfono: ");
            LocalDate birthDate = readDate("Fecha de nacimiento (dd/MM/yyyy): ");
            String address = readString("Dirección: ");
            Role role = readRole();
            
            User user = new User(id, fullName, email, phoneNumber, birthDate, address, role);
            User createdUser = userManagementUseCase.createUser(user);
            
            System.out.println("✅ Usuario creado exitosamente: " + createdUser.getFullName());
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listUsers() {
        System.out.println("\n=== LISTA DE USUARIOS ===");
        
        List<User> users = userManagementUseCase.getAllUsers();
        
        if (users.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        for (User user : users) {
            System.out.println("Cédula: " + user.getId() + 
                             " | Nombre: " + user.getFullName() + 
                             " | Rol: " + user.getRole().getDescription());
        }
    }
    
    private void searchUser() {
        System.out.println("\n=== BUSCAR USUARIO ===");
        
        String id = readString("Ingrese la cédula: ");
        User user = userManagementUseCase.findUserById(id);
        
        if (user != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("Cédula: " + user.getId());
            System.out.println("Nombre: " + user.getFullName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Teléfono: " + user.getPhoneNumber());
            System.out.println("Rol: " + user.getRole().getDescription());
        } else {
            System.out.println("❌ No se encontró un usuario con esa cédula.");
        }
    }
    
    private void deleteUser() {
        System.out.println("\n=== ELIMINAR USUARIO ===");
        
        String id = readString("Ingrese la cédula del usuario a eliminar: ");
        boolean deleted = userManagementUseCase.deleteUser(id);
        
        if (deleted) {
            System.out.println("✅ Usuario eliminado exitosamente.");
        } else {
            System.out.println("❌ No se encontró un usuario con esa cédula.");
        }
    }
    
    private void patientManagementMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE PACIENTES ===");
            System.out.println("1. Registrar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Buscar Paciente");
            System.out.println("4. Generar Factura");
            System.out.println("5. Volver al Menú Principal");
            
            int option = readInt("Seleccione una opción: ");
            
            switch (option) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    listPatients();
                    break;
                case 3:
                    searchPatient();
                    break;
                case 4:
                    generateBill();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void registerPatient() {
        System.out.println("\n=== REGISTRAR PACIENTE ===");
        
        try {
            String id = readString("Cédula: ");
            String username = readString("Nombre de usuario (máx 15 caracteres): ");
            String password = readString("Contraseña (mín 8 caracteres, incluir mayúscula, número y carácter especial): ");
            String fullName = readString("Nombre completo: ");
            LocalDate birthDate = readDate("Fecha de nacimiento (dd/MM/yyyy): ");
            Gender gender = readGender();
            String address = readString("Dirección: ");
            String phoneNumber = readString("Teléfono (10 dígitos): ");
            String email = readString("Email: ");
            
            Patient patient = new Patient(id, username, password, fullName, birthDate, 
                                        gender, address, phoneNumber, email);
            
            System.out.println("\n--- Información adicional (opcional) ---");
            String emergencyName = readString("Nombre contacto de emergencia (Enter para omitir): ");
            if (!emergencyName.trim().isEmpty()) {
                String emergencyRelation = readString("Relación: ");
                String emergencyPhone = readString("Teléfono de emergencia: ");
                EmergencyContact emergencyContact = new EmergencyContact(
                    emergencyName, emergencyRelation, emergencyPhone
                );
                patient.setEmergencyContact(emergencyContact);
            }
            
            String insuranceCompany = readString("Compañía de seguros (Enter para omitir): ");
            if (!insuranceCompany.trim().isEmpty()) {
                String policyNumber = readString("Número de póliza: ");
                boolean isActive = readBoolean("¿Póliza activa? (s/n): ");
                LocalDate expiryDate = null;
                if (isActive) {
                    expiryDate = readDate("Fecha de vencimiento (dd/MM/yyyy): ");
                }
                Insurance insurance = new Insurance(
                    insuranceCompany, policyNumber, isActive, expiryDate
                );
                patient.setInsurance(insurance);
            }
            
            Patient createdPatient = patientManagementUseCase.registerPatient(patient);
            System.out.println("✅ Paciente registrado exitosamente: " + createdPatient.getFullName());
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listPatients() {
        System.out.println("\n=== LISTA DE PACIENTES ===");
        
        List<Patient> patients = patientManagementUseCase.getAllPatients();
        
        if (patients.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        
        for (Patient patient : patients) {
            System.out.println("Cédula: " + patient.getId() + 
                             " | Nombre: " + patient.getFullName() + 
                             " | Género: " + patient.getGender().getDescription());
        }
    }
    
    private void searchPatient() {
        System.out.println("\n=== BUSCAR PACIENTE ===");
        
        String id = readString("Ingrese la cédula: ");
        Patient patient = patientManagementUseCase.findPatientById(id);
        
        if (patient != null) {
            System.out.println("Paciente encontrado:");
            System.out.println("Cédula: " + patient.getId());
            System.out.println("Nombre: " + patient.getFullName());
            System.out.println("Username: " + patient.getUsername());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Teléfono: " + patient.getPhoneNumber());
            System.out.println("Género: " + patient.getGender().getDescription());
            System.out.println("Edad: " + patient.getAge() + " años");
        } else {
            System.out.println("❌ No se encontró un paciente con esa cédula.");
        }
    }
    
    private void generateBill() {
        System.out.println("\n=== GENERAR FACTURA ===");
        
        String id = readString("Ingrese la cédula del paciente: ");
        String bill = patientManagementUseCase.generateBill(id);
        System.out.println(bill);
    }
    
    // === MÉTODOS AUXILIARES ===
    
    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }
    
    private LocalDate readDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                String dateStr = readString(prompt);
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use dd/MM/yyyy");
            }
        }
    }
    
    private boolean readBoolean(String prompt) {
        while (true) {
            String response = readString(prompt).toLowerCase();
            if (response.equals("s") || response.equals("si") || response.equals("sí")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.println("Responda 's' para sí o 'n' para no.");
            }
        }
    }
    
    private Role readRole() {
        System.out.println("Roles disponibles:");
        Role[] roles = Role.values();
        for (int i = 0; i < roles.length; i++) {
            System.out.println((i + 1) + ". " + roles[i].getDescription());
        }
        
        while (true) {
            int option = readInt("Seleccione el rol (1-" + roles.length + "): ");
            if (option >= 1 && option <= roles.length) {
                return roles[option - 1];
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
    
    private Gender readGender() {
        System.out.println("Géneros disponibles:");
        System.out.println("1. " + Gender.MALE.getDescription());
        System.out.println("2. " + Gender.FEMALE.getDescription());
        System.out.println("3. " + Gender.OTHER.getDescription());
        
        while (true) {
            int option = readInt("Seleccione el género (1-3): ");
            switch (option) {
                case 1: return Gender.MALE;
                case 2: return Gender.FEMALE;
                case 3: return Gender.OTHER;
                default: System.out.println("Opción inválida.");
            }
        }
    }
}