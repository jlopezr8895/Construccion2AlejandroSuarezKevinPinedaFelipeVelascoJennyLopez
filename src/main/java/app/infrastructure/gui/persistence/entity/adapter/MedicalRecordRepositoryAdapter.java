
package app.infrastructure.persistence.adapter;

import app.domain.model.MedicalRecord;
import app.domain.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


 // solo guarda en memoria, no usa JPA, no hay conexión a MySQL.
 

//Repository le dice a Spring que esta clase es un componente de persistencia.
@Repository
public class MedicalRecordRepositoryAdapter implements MedicalRecordRepository {
    
    //El ConcurrentHashMap funciona como una base de datos simulada. 
    //Se usa como una implementación simple para pruebas, antes de conectar con una bd real.
    private final ConcurrentHashMap<String, MedicalRecord> records = new ConcurrentHashMap<>();
    
    @Override
    //Guarda o actualiza un registro médico en el mapa, usando el id como clave.
    public MedicalRecord save(MedicalRecord medicalRecord) {
        records.put(medicalRecord.getId(), medicalRecord);
        return medicalRecord;
    }
    
    @Override
    /*Busca un registro médico por su id.
    Si lo encuentra lo envuelve en un Optional.
    Si no existe devuelve un Optional.empty().*/
    public Optional<MedicalRecord> findById(String id) {
        return Optional.ofNullable(records.get(id));
    }
    
    @Override
    //Filtra todos los registros y devuelve los que pertenecen a un paciente específico por cédula.
    public List<MedicalRecord> findByPatientId(String patientId) {
        return records.values().stream()
                .filter(record -> patientId.equals(record.getPatientId()))
                .toList();
    }
    
    @Override
    //Filtra todos los registros y devuelve los que fueron atendidos por un médico específico por cédula.
    public List<MedicalRecord> findByDoctorId(String doctorId) {
        return records.values().stream()
                .filter(record -> doctorId.equals(record.getDoctorId()))
                .toList();
    }
}

/*Ambos son adaptadores, pero la diferencia está en la tecnología de persistencia.
En MedicalRecordRepositoryAdapter usamos un ConcurrentHashMap, que solo simula una base de 
datos en memoria, y los datos se pierden al reiniciar.
En cambio, OrderRepositoryAdapter usa Spring Data JPA con OrderEntity y OrderJpaRepository, 
lo que lo conecta directamente a la base de datos MySQL real configurada en application.properties*/

/*Aunque MedicalRecordRepositoryAdapter no se conecta a la base de datos real, se marca con @Repository
porque representa la capa de persistencia. En este caso la persistencia es en memoria usando un mapa,
pero sigue cumpliendo el contrato de repositorio del dominio.
De esta manera, Spring la detecta, permite inyectarla como dependencia y mantiene la arquitectura 
clara. Más adelante, si se reemplaza por una implementación con JPA y MySQL, el cambio sería
transparente para el resto del sistema.*/