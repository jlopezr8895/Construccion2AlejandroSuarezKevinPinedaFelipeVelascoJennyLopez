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

/**
 * Adaptador que conecta el dominio con la base de datos real para la entidad User.
 * Implementa la interfaz UserRepository usando Spring Data JPA.
 * Convierte entre objetos de dominio (User) y entidades JPA (UserEntity).
 */
@Repository
public class UserRepositoryAdapter implements UserRepository {
    
    // Dependencias necesarias
    private final UserJpaRepository userJpaRepository; // Acceso a la BD usando Spring Data JPA
    private final UserMapper userMapper;               // Conversión entre User (dominio) y UserEntity (BD)
    
    /**
     * Constructor con inyección de dependencias.
     * Spring pasa automáticamente las implementaciones necesarias.
     */
    @Autowired
    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }
    
    /**
     * Guarda un usuario en la base de datos.
     * - Convierte el objeto de dominio (User) a entidad JPA (UserEntity).
     * - Usa JPA para persistirlo en la tabla "users".
     * - Convierte la entidad guardada de nuevo a objeto de dominio.
     */
    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }
    
    /**
     * Busca un usuario en la base de datos por su ID.
     * Si existe, lo devuelve como objeto de dominio.
     * Si no, devuelve un Optional vacío.
     */
    @Override
    public Optional<User> findById(String id) {
        return userJpaRepository.findById(id)
                .map(userMapper::toDomain);
    }
    
    /**
     * Obtiene todos los usuarios de la base de datos.
     * Convierte cada entidad JPA a un objeto de dominio (User).
     */
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    /**
     * Busca todos los usuarios que tengan un rol específico.
     * Ejemplo: todos los ADMINISTRADORES o todos los PACIENTES.
     */
    public List<User> findByRole(Role role) {
        return userJpaRepository.findByRole(role)
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    /**
     * Elimina un usuario de la base de datos por su ID.
     */
    @Override
    public void deleteById(String id) {
        userJpaRepository.deleteById(id);
    }
    
    /**
     * Verifica si un usuario existe en la base de datos por su ID.
     * Devuelve true si existe, false si no.
     */
    @Override
    public boolean existsById(String id) {
        return userJpaRepository.existsById(id);
    }
}
