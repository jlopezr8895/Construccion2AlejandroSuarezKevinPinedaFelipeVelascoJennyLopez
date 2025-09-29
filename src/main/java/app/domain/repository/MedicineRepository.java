/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.repository;

/**
 *
 * @author Asus
 */
import app.domain.model.Medicine;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository {
    Medicine save(Medicine medicine);
    Optional<Medicine> findById(String id);
    List<Medicine> findAll();
    List<Medicine> findByNameContaining(String name);
    boolean existsById(String id);
    void deleteById(String id);
}