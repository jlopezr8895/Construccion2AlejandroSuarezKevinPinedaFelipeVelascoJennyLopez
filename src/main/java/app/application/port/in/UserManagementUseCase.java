package app.application.port.in;

import app.domain.model.User;
import app.domain.model.valueObjects.Role;
import java.util.List;

public interface UserManagementUseCase {
    User createUser(User user);
    List<User> getAllUsers();
    List<User> getUsersByRole(Role role);
    boolean deleteUser(String userId);
    User findUserById(String userId);
}