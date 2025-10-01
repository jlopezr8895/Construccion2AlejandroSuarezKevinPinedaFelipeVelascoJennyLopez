package app.infrastructure.gui.persistence.entity.mapper;

import app.domain.model.User;
import app.infrastructure.gui.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
/*Verifica si user es null para evitar errores.
Crea un UserEntity copiando todos los datos del User.
Devuelve el UserEntity, listo para guardar en la base de datos.*/
    
    public UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getBirthDate(),
            user.getAddress(),  
            user.getRole()
        );
    }
/*Verifica si userEntity es null.
Crea un User copiando los datos del UserEntity.
Devuelve un User listo para trabajar en la lógica de la aplicación.*/
    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) return null;
        return new User(
            userEntity.getId(),
            userEntity.getFullName(),
            userEntity.getEmail(),
            userEntity.getPhoneNumber(),
            userEntity.getBirthDate(),
            userEntity.getAddress(),
            userEntity.getRole()
        );
    }
}