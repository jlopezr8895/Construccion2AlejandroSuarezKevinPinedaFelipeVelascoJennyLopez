
package app.application.usecase;
 
import app.application.port.in.MedicalAttentionUseCase;
import app.domain.model.MedicalRecord;
import app.domain.model.Order;
import app.domain.model.VitalSigns;
import java.util.List;
import org.springframework.stereotype.Service;

//@Service le dice a Spring que esta clase es un servicio de negocio, y que puede inyectarse en otras partes con @Autowired.
@Service
public class MedicalAttentionService implements MedicalAttentionUseCase {

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<MedicalRecord> getPatientMedicalHistory(String patientId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Order createMedicalOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Order> getPatientOrders(String patientId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public VitalSigns recordVitalSigns(VitalSigns vitalSigns) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<VitalSigns> getPatientVitalSigns(String patientId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean recordMedicineAdministration(String orderNumber, Integer itemNumber, String nurseId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }      
}