package app.infrastructure.gui.persistence.entity.adapter;

import app.domain.model.User;
import app.domain.model.valueObjects.Role;
import app.domain.repository.UserRepository;
import app.infrastructure.gui.persistence.entity.jpa.UserJpaRepository;
import app.infrastructure.gui.persistence.entity.UserEntity;
import app.infrastructure.gui.persistence.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryAdapter implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    
    @Autowired
    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }
    
    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findById(String id) {
        return userJpaRepository.findById(id)
                .map(userMapper::toDomain);
    }
    
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    public List<User> findByRole(Role role) {
        return userJpaRepository.findByRole(role)
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(String id) {
        userJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(String id) {
        return userJpaRepository.existsById(id);
    }
}
