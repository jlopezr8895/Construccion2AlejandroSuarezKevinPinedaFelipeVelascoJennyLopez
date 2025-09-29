/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.gui.persistence.entity.jpa;

import app.infrastructure.gui.persistence.entity.VitalSignsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VitalSignsJpaRepository extends JpaRepository<VitalSignsEntity, String> {
    List<VitalSignsEntity> findByPatientId(String patientId);
    List<VitalSignsEntity> findByNurseId(String nurseId);
}