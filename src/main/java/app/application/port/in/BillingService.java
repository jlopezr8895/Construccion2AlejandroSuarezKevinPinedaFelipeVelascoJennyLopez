
package app.application.port.in;

import app.domain.model.Patient;
import app.domain.model.Order;
import app.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingService implements BillingUseCase {
    
    private final PatientManagementUseCase patientManagementUseCase;
    private final OrderRepository orderRepository;
    
    // Constantes de facturación
    private static final double COPAYMENT_AMOUNT = 50000.0; // $50,000 copago
    private static final double ANNUAL_COPAYMENT_LIMIT = 1000000.0; // $1,000,000 límite anual
    
    @Autowired
    public BillingService(PatientManagementUseCase patientManagementUseCase,
                         OrderRepository orderRepository) {
        this.patientManagementUseCase = patientManagementUseCase;
        this.orderRepository = orderRepository;
    }
    
    @Override
    public String generateDetailedBill(String patientId, String doctorName) {
        Patient patient = patientManagementUseCase.findPatientById(patientId);
        if (patient == null) {
            return "❌ No se encontró el paciente con cédula: " + patientId;
        }
        
        StringBuilder bill = new StringBuilder();
        bill.append("========================================\n");
        bill.append("           FACTURA DETALLADA           \n");
        bill.append("========================================\n");
        bill.append("Fecha de emisión: ").append(LocalDate.now()).append("\n");
        bill.append("Paciente: ").append(patient.getFullName()).append("\n");
        bill.append("Cédula: ").append(patient.getId()).append("\n");
        bill.append("Edad: ").append(patient.getAge()).append(" años\n");
        bill.append("Médico tratante: ").append(doctorName != null ? doctorName : "No especificado").append("\n\n");
        
        // Información del seguro
        if (patient.getInsurance() != null) {
            bill.append("=== INFORMACIÓN DEL SEGURO ===\n");
            bill.append("Compañía: ").append(patient.getInsurance().getCompanyName()).append("\n");
            bill.append("Número de póliza: ").append(patient.getInsurance().getPolicyNumber()).append("\n");
            
            if (patient.getInsurance().isCurrentlyActive()) {
                long daysToExpiry = ChronoUnit.DAYS.between(LocalDate.now(), patient.getInsurance().getExpiryDate());
                bill.append("Estado: ACTIVA\n");
                bill.append("Días de vigencia: ").append(daysToExpiry).append("\n");
                bill.append("Fecha de vencimiento: ").append(patient.getInsurance().getExpiryDate()).append("\n\n");
            } else {
                bill.append("Estado: INACTIVA\n\n");
            }
        }
        
        // Calcular totales
        double totalAmount = calculateTotalAmount(patientId);
        double copayment = calculateCopayment(patient, totalAmount);
        boolean exceededLimit = hasExceededAnnualCopaymentLimit(patientId);
        
        bill.append("=== DETALLE DE SERVICIOS ===\n");
        bill.append("Total de servicios prestados: $").append(String.format("%.2f", totalAmount)).append("\n");
        
        if (patient.getInsurance() != null && patient.getInsurance().isCurrentlyActive()) {
            if (exceededLimit) {
                bill.append("Copago: $0.00 (Límite anual superado)\n");
                bill.append("A cargo del seguro: $").append(String.format("%.2f", totalAmount)).append("\n");
                bill.append("TOTAL A PAGAR: $0.00\n");
            } else {
                bill.append("Copago: $").append(String.format("%.2f", copayment)).append("\n");
                bill.append("A cargo del seguro: $").append(String.format("%.2f", totalAmount - copayment)).append("\n");
                bill.append("TOTAL A PAGAR: $").append(String.format("%.2f", copayment)).append("\n");
            }
        } else {
            bill.append("Sin seguro médico\n");
            bill.append("TOTAL A PAGAR: $").append(String.format("%.2f", totalAmount)).append("\n");
        }
        
        bill.append("========================================\n");
        
        return bill.toString();
    }
    
    @Override
    public double calculateTotalAmount(String patientId) {
        // Para simplificar, calculamos un monto base por las órdenes del paciente
        List<Order> orders = orderRepository.findByPatientId(patientId);
        
        // Costo base por tipo de orden (valores de ejemplo)
        double totalAmount = 0.0;
        for (Order order : orders) {
            switch (order.getOrderType().toUpperCase()) {
                case "MEDICINE":
                    totalAmount += 25000.0; // $25,000 por medicamentos
                    break;
                case "PROCEDURE":
                    totalAmount += 150000.0; // $150,000 por procedimientos
                    break;
                case "DIAGNOSTIC_AID":
                    totalAmount += 80000.0; // $80,000 por ayudas diagnósticas
                    break;
                default:
                    totalAmount += 50000.0; // $50,000 por defecto
                    break;
            }
        }
        
        // Si no hay órdenes, cobrar consulta básica
        if (totalAmount == 0.0) {
            totalAmount = 120000.0; // $120,000 consulta básica
        }
        
        return totalAmount;
    }
    
    @Override
    public double calculateCopayment(Patient patient, double totalAmount) {
        if (patient.getInsurance() == null || !patient.getInsurance().isCurrentlyActive()) {
            return totalAmount; // Sin seguro, paga todo
        }
        
        if (hasExceededAnnualCopaymentLimit(patient.getId())) {
            return 0.0; // No paga copago si superó el límite anual
        }
        
        return Math.min(COPAYMENT_AMOUNT, totalAmount);
    }
    
    @Override
    public boolean hasExceededAnnualCopaymentLimit(String patientId) {
        List<Double> annualPayments = getAnnualPaymentHistory(patientId);
        double totalPaid = annualPayments.stream().mapToDouble(Double::doubleValue).sum();
        return totalPaid >= ANNUAL_COPAYMENT_LIMIT;
    }
    
    @Override
    public List<Double> getAnnualPaymentHistory(String patientId) {
        // Para simplificar, simulamos un historial de pagos
        // En un proyecto real, tendrías una tabla de pagos con fechas
        
        // Simulamos que el paciente ha pagado copagos por cada orden del año actual
        List<Order> currentYearOrders = orderRepository.findByPatientId(patientId)
                .stream()
                .filter(order -> order.getCreationDate() != null && 
                               order.getCreationDate().getYear() == LocalDate.now().getYear())
                .collect(Collectors.toList());
        
        return currentYearOrders.stream()
                .map(order -> COPAYMENT_AMOUNT)
                .collect(Collectors.toList());
    }
    
    @Override
    public String generateBillingReport(String startDate, String endDate) {
        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("      REPORTE DE FACTURACIÓN           \n");
        report.append("========================================\n");
        report.append("Período: ").append(startDate).append(" a ").append(endDate).append("\n");
        report.append("Fecha del reporte: ").append(LocalDate.now()).append("\n\n");
        
        // Para simplificar, mostramos un reporte básico
        report.append("📊 RESUMEN:\n");
        report.append("- Total de pacientes atendidos: En desarrollo\n");
        report.append("- Total facturado: En desarrollo\n");
        report.append("- Total de copagos: En desarrollo\n");
        report.append("- Total a cargo de seguros: En desarrollo\n\n");
        
        report.append("ℹ️ Nota: Funcionalidad de reportes en desarrollo\n");
        report.append("========================================\n");
        
        return report.toString();
    }
}