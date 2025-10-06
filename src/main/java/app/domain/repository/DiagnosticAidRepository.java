
package app.domain.repository;

import app.domain.model.DiagnosticAid;
import java.util.List;
import java.util.Optional;

public interface DiagnosticAidRepository {
    DiagnosticAid save(DiagnosticAid diagnosticAid);
    Optional<DiagnosticAid> findById(String id);
    List<DiagnosticAid> findAll();
    List<DiagnosticAid> findByNameContaining(String name);
    List<DiagnosticAid> findByRequiresSpecialist(boolean requiresSpecialist);
    boolean existsById(String id);
    void deleteById(String id);
}
