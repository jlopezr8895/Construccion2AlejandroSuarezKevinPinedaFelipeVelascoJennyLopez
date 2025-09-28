
package app.application.port.in;

import app.domain.model.Patient;
import java.util.List;

/**
 * Define las operaciones de facturación
 * Usada por el rol ADMINISTRATIVE
 */
public interface BillingUseCase {
    
    /**
     * Genera una factura detallada para un paciente
     * @param patientId Cédula del paciente
     * @param doctorName Nombre del médico tratante
     * @return String con la factura formateada
     */
    String generateDetailedBill(String patientId, String doctorName);
    
    /**
     * Calcula el total a pagar por un paciente
     * @param patientId Cédula del paciente
     * @return Monto total a pagar
     */
    double calculateTotalAmount(String patientId);
    
    /**
     * Calcula el copago de un paciente (si tiene seguro activo)
     * @param patient Los datos del paciente
     * @param totalAmount Monto total de los servicios
     * @return Monto del copago
     */
    double calculateCopayment(Patient patient, double totalAmount);
    
    /**
     * Verifica si un paciente ha superado el límite anual de copago
     * @param patientId Cédula del paciente
     * @return true si ha superado el límite (1 millón)
     */
    boolean hasExceededAnnualCopaymentLimit(String patientId);
    
    /**
     * Obtiene el historial de pagos de un paciente en el año actual
     * @param patientId Cédula del paciente
     * @return Lista de montos pagados
     */
    List<Double> getAnnualPaymentHistory(String patientId);
    
    /**
     * Genera reporte de facturación por rango de fechas
     * @param startDate Fecha inicio (formato: yyyy-MM-dd)
     * @param endDate Fecha fin (formato: yyyy-MM-dd)
     * @return String con el reporte
     */
    String generateBillingReport(String startDate, String endDate);
}