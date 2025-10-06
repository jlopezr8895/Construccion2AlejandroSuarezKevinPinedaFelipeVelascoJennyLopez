package app.infrastructure.persistence.entity.mapper;

import app.domain.model.Procedure;
import app.infrastructure.persistence.entity.ProcedureEntity;
import org.springframework.stereotype.Component;

@Component
public class ProcedureMapper {
    
    public ProcedureEntity toEntity(Procedure procedure) {
        if (procedure == null) return null;
        return new ProcedureEntity(
            procedure.getId(),
            procedure.getName(),
            procedure.getDescription(),
            procedure.getCost(),
            procedure.isRequiresSpecialist()
        );
    }
    
    public Procedure toDomain(ProcedureEntity entity) {
        if (entity == null) return null;
        return new Procedure(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getCost(),
            entity.getRequiresSpecialist()
        );
    }
}
