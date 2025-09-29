/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.repository;

import app.domain.model.VitalSigns;
import java.util.List;
import java.util.Optional;

public interface VitalSignsRepository {
    VitalSigns save(VitalSigns vitalSigns);
    Optional<VitalSigns> findById(String id);
    List<VitalSigns> findByPatientId(String patientId);
    List<VitalSigns> findByNurseId(String nurseId);
    List<VitalSigns> findAll();
}
