package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.UserManagementUseCase;
import app.domain.model.User;
import app.domain.model.valueObjects.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final UserManagementUseCase userManagementUseCase;

    public AdminController(UserManagementUseCase userManagementUseCase) {
        this.userManagementUseCase = userManagementUseCase;
    }

    @PostMapping
    public ResponseEntity<User> createAdmin(@RequestBody User userRequest) {

        // Asignar rol ADMINISTRATIVE
        userRequest.setRole(Role.ADMINISTRATIVE);

        User created = userManagementUseCase.createUser(userRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
