package app.infrastructure.persistence.entity.mapper;

import app.domain.model.Medicine;
import app.infrastructure.persistence.entity.MedicineEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper {
    
    public MedicineEntity toEntity(Medicine medicine) {
        if (medicine == null) return null;
        return new MedicineEntity(
            medicine.getId(),
            medicine.getName(),
            medicine.getDescription(),
            medicine.getCost()
        );
    }
    
    public Medicine toDomain(MedicineEntity entity) {
        if (entity == null) return null;
        return new Medicine(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getCost()
        );
    }
}
