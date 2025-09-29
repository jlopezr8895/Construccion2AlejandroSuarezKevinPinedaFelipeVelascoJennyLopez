/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.gui.persistence.entity.jpa;

import app.infrastructure.gui.persistence.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicineJpaRepository extends JpaRepository<MedicineEntity, String> {
    List<MedicineEntity> findByNameContaining(String name);
}
