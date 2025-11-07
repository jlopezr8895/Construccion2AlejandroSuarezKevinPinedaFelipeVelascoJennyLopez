package app.infrastructure.gui;

import app.application.port.in.UserManagementUseCase;
import app.application.port.in.PatientManagementUseCase;
import app.application.port.in.InventoryManagementUseCase;
import app.application.port.in.NursingUseCase;
import app.application.port.in.MedicalAttentionUseCase;
import app.domain.model.*;
import app.domain.model.valueObjects.Role;
import app.domain.model.valueObjects.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
public class ClinicConsoleApp {
    
    private final UserManagementUseCase userManagementUseCase;
    private final PatientManagementUseCase patientManagementUseCase;
    private final InventoryManagementUseCase inventoryManagementUseCase;
    private final NursingUseCase nursingUseCase;
    private final MedicalAttentionUseCase medicalAttentionUseCase;
    private final Scanner scanner;
    
    @Autowired
    public ClinicConsoleApp(UserManagementUseCase userManagementUseCase, 
                           PatientManagementUseCase patientManagementUseCase,
                           InventoryManagementUseCase inventoryManagementUseCase,
                           NursingUseCase nursingUseCase,
                           MedicalAttentionUseCase medicalAttentionUseCase) {
        this.userManagementUseCase = userManagementUseCase;
        this.patientManagementUseCase = patientManagementUseCase;
        this.inventoryManagementUseCase = inventoryManagementUseCase;
        this.nursingUseCase = nursingUseCase;
        this.medicalAttentionUseCase = medicalAttentionUseCase;
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
                    inventoryManagementMenu();
                    break;
                case 4:
                    nursingMenu();
                    break;
                case 5:
                    medicalAttentionMenu();
                    break;
                case 6:
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
        System.out.println("3. Gestión de Inventarios (Soporte de Información)");
        System.out.println("4. Enfermería (Signos Vitales)");
        System.out.println("5. Atención Médica (Médicos)");
        System.out.println("6. Salir");
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
    
    // === MENÚ DE INVENTARIOS ===
    
    private void inventoryManagementMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE INVENTARIOS ===");
            System.out.println("1. Medicamentos");
            System.out.println("2. Procedimientos");
            System.out.println("3. Ayudas Diagnósticas");
            System.out.println("4. Volver al Menú Principal");
            
            int option = readInt("Seleccione una opción: ");
            
            switch (option) {
                case 1:
                    medicineInventoryMenu();
                    break;
                case 2:
                    procedureInventoryMenu();
                    break;
                case 3:
                    diagnosticAidInventoryMenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void medicineInventoryMenu() {
        while (true) {
            System.out.println("\n=== INVENTARIO DE MEDICAMENTOS ===");
            System.out.println("1. Crear Medicamento");
            System.out.println("2. Listar Medicamentos");
            System.out.println("3. Buscar Medicamento");
            System.out.println("4. Eliminar Medicamento");
            System.out.println("5. Volver");
            
            int option = readInt("Seleccione una opción: ");
            
            switch (option) {
                case 1:
                    createMedicine();
                    break;
                case 2:
                    listMedicines();
                    break;
                case 3:
                    searchMedicine();
                    break;
                case 4:
                    deleteMedicine();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void createMedicine() {
        System.out.println("\n=== CREAR MEDICAMENTO ===");
        
        try {
            String id = readString("ID del medicamento: ");
            String name = readString("Nombre: ");
            String description = readString("Descripción: ");
            double cost = readDouble("Costo: ");
            
            Medicine medicine = new Medicine(id, name, description, cost);
            Medicine created = inventoryManagementUseCase.createMedicine(medicine);
            
            System.out.println("✅ Medicamento creado: " + created.getName());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listMedicines() {
        System.out.println("\n=== LISTA DE MEDICAMENTOS ===");
        List<Medicine> medicines = inventoryManagementUseCase.getAllMedicines();
        
        if (medicines.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
            return;
        }
        
        for (Medicine medicine : medicines) {
            System.out.println("ID: " + medicine.getId() + 
                             " | Nombre: " + medicine.getName() + 
                             " | Costo: $" + medicine.getCost());
        }
    }
    
    private void searchMedicine() {
        System.out.println("\n=== BUSCAR MEDICAMENTO ===");
        String id = readString("ID del medicamento: ");
        Medicine medicine = inventoryManagementUseCase.findMedicineById(id);
        
        if (medicine != null) {
            System.out.println("Medicamento encontrado:");
            System.out.println("ID: " + medicine.getId());
            System.out.println("Nombre: " + medicine.getName());
            System.out.println("Descripción: " + medicine.getDescription());
            System.out.println("Costo: $" + medicine.getCost());
        } else {
            System.out.println("❌ No se encontró el medicamento.");
        }
    }
    
    private void deleteMedicine() {
        System.out.println("\n=== ELIMINAR MEDICAMENTO ===");
        String id = readString("ID del medicamento: ");
        boolean deleted = inventoryManagementUseCase.deleteMedicine(id);
        
        if (deleted) {
            System.out.println("✅ Medicamento eliminado.");
        } else {
            System.out.println("❌ No se encontró el medicamento.");
        }
    }
    
    private void procedureInventoryMenu() {
        System.out.println("\n💡 Funcionalidad de Procedimientos - Similar a medicamentos");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    private void diagnosticAidInventoryMenu() {
        System.out.println("\n💡 Funcionalidad de Ayudas Diagnósticas - Similar a medicamentos");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    // === MENÚ DE ENFERMERÍA ===
    
    private void nursingMenu() {
        while (true) {
            System.out.println("\n=== ENFERMERÍA ===");
            System.out.println("1. Registrar Signos Vitales");
            System.out.println("2. Ver Signos Vitales de Paciente");
            System.out.println("3. Volver al Menú Principal");
            
            int option = readInt("Seleccione una opción: ");
            
            switch (option) {
                case 1:
                    recordVitalSigns();
                    break;
                case 2:
                    viewPatientVitalSigns();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void recordVitalSigns() {
        System.out.println("\n=== REGISTRAR SIGNOS VITALES ===");
        
        try {
            String patientId = readString("Cédula del paciente: ");
            String nurseId = readString("Cédula de la enfermera: ");
            String bloodPressure = readString("Presión arterial (ej: 120/80): ");
            double temperature = readDouble("Temperatura (°C): ");
            int pulse = readInt("Pulso (ppm): ");
            int oxygenLevel = readInt("Nivel de oxígeno (%): ");
            
            VitalSigns vitalSigns = new VitalSigns(
                null, patientId, nurseId, LocalDateTime.now(),
                bloodPressure, temperature, pulse, oxygenLevel
            );
            
            VitalSigns recorded = nursingUseCase.recordVitalSigns(vitalSigns);
            System.out.println("✅ Signos vitales registrados exitosamente." + " el dia: " + recorded.getRecordDate());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void viewPatientVitalSigns() {
        System.out.println("\n=== VER SIGNOS VITALES ===");
        String patientId = readString("Cédula del paciente: ");
        List<VitalSigns> vitalSignsList = nursingUseCase.getPatientVitalSigns(patientId);
        
        if (vitalSignsList.isEmpty()) {
            System.out.println("No hay signos vitales registrados para este paciente.");
            return;
        }
        
        for (VitalSigns vs : vitalSignsList) {
            System.out.println("\n📊 Registro: " + vs.getRecordDate());
            System.out.println("Presión arterial: " + vs.getBloodPressure());
            System.out.println("Temperatura: " + vs.getTemperature() + "°C");
            System.out.println("Pulso: " + vs.getPulse() + " ppm");
            System.out.println("Oxígeno: " + vs.getOxygenLevel() + "%");
            System.out.println("Enfermera: " + vs.getNurseId());
        }
    }
    
    // === MENÚ DE ATENCIÓN MÉDICA ===
    
    private void medicalAttentionMenu() {
        while (true) {
            System.out.println("\n=== ATENCIÓN MÉDICA ===");
            System.out.println("1. Crear Historia Clínica");
            System.out.println("2. Ver Historia Clínica de Paciente");
            System.out.println("3. Crear Orden Médica");
            System.out.println("4. Ver Órdenes de Paciente");
            System.out.println("5. Volver al Menú Principal");
            
            int option = readInt("Seleccione una opción: ");
            
            switch (option) {
                case 1:
                    createMedicalRecord();
                    break;
                case 2:
                    viewMedicalHistory();
                    break;
                case 3:
                    createMedicalOrder();
                    break;
                case 4:
                    viewPatientOrders();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void createMedicalRecord() {
        System.out.println("\n=== CREAR HISTORIA CLÍNICA ===");
        
        try {
            String patientId = readString("Cédula del paciente: ");
            String doctorId = readString("Cédula del médico: ");
            String reason = readString("Motivo de consulta: ");
            String symptoms = readString("Sintomatología: ");
            String diagnosis = readString("Diagnóstico: ");
            
            MedicalRecord record = new MedicalRecord(
                null, patientId, doctorId, LocalDate.now(),
                reason, symptoms, diagnosis
            );
            
            MedicalRecord created = medicalAttentionUseCase.createMedicalRecord(record);
			System.out.println("✅ Historia clínica creada exitosamente." + " el dia: " + created.getDate());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void viewMedicalHistory() {
        System.out.println("\n=== VER HISTORIA CLÍNICA ===");
        String patientId = readString("Cédula del paciente: ");
        List<MedicalRecord> records = medicalAttentionUseCase.getPatientMedicalHistory(patientId);
        
        if (records.isEmpty()) {
            System.out.println("No hay registros médicos para este paciente.");
            return;
        }
        
        for (MedicalRecord record : records) {
            System.out.println("\n📋 Fecha: " + record.getDate());
            System.out.println("Médico: " + record.getDoctorId());
            System.out.println("Motivo: " + record.getReason());
            System.out.println("Síntomas: " + record.getSymptoms());
            System.out.println("Diagnóstico: " + record.getDiagnosis());
            System.out.println("-----------------------------------");
        }
    }
    
    private void createMedicalOrder() {
        System.out.println("\n=== CREAR ORDEN MÉDICA ===");
        
        try {
            String orderNumber = readString("Número de orden (máx 6 dígitos): ");
            String patientId = readString("Cédula del paciente: ");
            String doctorId = readString("Cédula del médico: ");
            
            System.out.println("\nTipo de orden:");
            System.out.println("1. MEDICINE (Medicamento)");
            System.out.println("2. PROCEDURE (Procedimiento)");
            System.out.println("3. DIAGNOSTIC_AID (Ayuda Diagnóstica)");
            int typeOption = readInt("Seleccione: ");
            
            String orderType = switch(typeOption) {
                case 1 -> "MEDICINE";
                case 2 -> "PROCEDURE";
                case 3 -> "DIAGNOSTIC_AID";
                default -> "MEDICINE";
            };
            
            Order order = new Order(orderNumber, patientId, doctorId, 
                                   LocalDate.now(), orderType);
            
            Order created = medicalAttentionUseCase.createMedicalOrder(order);
            System.out.println("✅ Orden médica creada: " + created.getOrderNumber());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void viewPatientOrders() {
        System.out.println("\n=== VER ÓRDENES DE PACIENTE ===");
        String patientId = readString("Cédula del paciente: ");
        List<Order> orders = medicalAttentionUseCase.getPatientOrders(patientId);
        
        if (orders.isEmpty()) {
            System.out.println("No hay órdenes para este paciente.");
            return;
        }
        
        for (Order order : orders) {
            System.out.println("\n📄 Orden #" + order.getOrderNumber());
            System.out.println("Fecha: " + order.getCreationDate());
            System.out.println("Tipo: " + order.getOrderType());
            System.out.println("Médico: " + order.getDoctorId());
        }
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
    
    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
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