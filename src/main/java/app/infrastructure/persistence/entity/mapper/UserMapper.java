package app.infrastructure.persistence.entity.mapper;

import app.domain.model.User;
import app.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
            user.getBirthDate(),
            user.getAddress(),
            user.getRole()
        );
    }
    
    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) return null;
        return new User(
            userEntity.getId(),
            userEntity.getFullName(),
            userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getPhoneNumber(),
            userEntity.getBirthDate(),
            userEntity.getAddress(),
            userEntity.getRole()
        );
    }
}