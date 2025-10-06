package app.infrastructure.persistence.entity.mapper;

import app.domain.model.DiagnosticAid;
import app.infrastructure.persistence.entity.DiagnosticAidEntity;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticAidMapper {
    
    public DiagnosticAidEntity toEntity(DiagnosticAid diagnosticAid) {
        if (diagnosticAid == null) return null;
        return new DiagnosticAidEntity(
            diagnosticAid.getId(),
            diagnosticAid.getName(),
            diagnosticAid.getDescription(),
            diagnosticAid.getCost(),
            diagnosticAid.isRequiresSpecialist()
        );
    }
    
    public DiagnosticAid toDomain(DiagnosticAidEntity entity) {
        if (entity == null) return null;
        return new DiagnosticAid(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getCost(),
            entity.getRequiresSpecialist()
        );
    }
}
