/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.repository;

import app.domain.model.Procedure;
import java.util.List;
import java.util.Optional;

public interface ProcedureRepository {
    Procedure save(Procedure procedure);
    Optional<Procedure> findById(String id);
    List<Procedure> findAll();
    List<Procedure> findByNameContaining(String name);
    List<Procedure> findByRequiresSpecialist(boolean requiresSpecialist);
    boolean existsById(String id);
    void deleteById(String id);
}
