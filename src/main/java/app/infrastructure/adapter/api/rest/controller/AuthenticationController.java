package app.infrastructure.adapter.api.rest.controller;

import app.application.port.in.AuthenticationUseCase;
import app.infrastructure.adapter.api.rest.dto.auth.LoginRequest;
import app.infrastructure.adapter.api.rest.dto.auth.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    public AuthenticationController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authenticationUseCase.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
