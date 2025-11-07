package app.application.port.usecase;

import app.application.port.in.UserManagementUseCase;
import app.domain.model.User;
import app.domain.model.valueObjects.Role;
import app.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserManagementService implements UserManagementUseCase {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User createUser(User user) {
        if (!user.isValid()) {
            throw new IllegalArgumentException("Los datos del usuario no son válidos");
        }
        if (userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("Ya existe un usuario con la cédula: " + user.getId());
        }
        return userRepository.save(user);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
    
    @Override
    public boolean deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            return false;
        }
        userRepository.deleteById(userId);
        return true;
    }
    
    @Override
    public User findUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }
}