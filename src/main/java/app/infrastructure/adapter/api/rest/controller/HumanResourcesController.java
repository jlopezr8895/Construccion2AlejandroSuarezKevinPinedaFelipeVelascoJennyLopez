package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.UserManagementUseCase;
import app.domain.model.User;
import app.domain.model.valueObjects.Role;
import app.infrastructure.adapter.api.rest.dto.user.CreateUserRequest;
import app.infrastructure.adapter.api.rest.dto.user.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@org.springframework.security.access.prepost.PreAuthorize("hasRole('HUMAN_RESOURCES')")
@RequestMapping("/api/users")
public class HumanResourcesController {

    private final UserManagementUseCase userManagementUseCase;

    public HumanResourcesController(UserManagementUseCase userManagementUseCase) {
        this.userManagementUseCase = userManagementUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = new User(
            request.getId(),
            request.getFullName(),
            request.getEmail(),
                request.getPassword(),
            request.getPhoneNumber(),
            request.getBirthDate(),
            request.getAddress(),
            request.getRole()
        );

        User createdUser = userManagementUseCase.createUser(user);
        UserResponse response = mapToUserResponse(createdUser);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userManagementUseCase.getAllUsers();
        List<UserResponse> response = users.stream()
            .map(this::mapToUserResponse)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        User user = userManagementUseCase.findUserById(id);
        
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        
        UserResponse response = mapToUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponse>> getUsersByRole(@PathVariable Role role) {
        List<User> users = userManagementUseCase.getUsersByRole(role);
        List<UserResponse> response = users.stream()
            .map(this::mapToUserResponse)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean deleted = userManagementUseCase.deleteUser(id);
        
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getBirthDate(),
            user.getAddress(),
            user.getRole()
        );
    }
}
