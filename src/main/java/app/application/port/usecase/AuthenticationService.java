package app.application.port.usecase;

import app.application.port.in.AuthenticationUseCase;
import app.domain.model.User;
import app.domain.repository.UserRepository;
import app.infrastructure.adapter.api.rest.dto.auth.LoginRequest;
import app.infrastructure.adapter.api.rest.dto.auth.LoginResponse;
import app.infrastructure.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationUseCase {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // Buscar usuario por email
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        // Validar contraseña (comparación directa como se solicitó)
        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        // Generar token JWT con el rol (sin prefijo, se agregará en JwtUtil)
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        // Retornar respuesta con token y datos del usuario
        return new LoginResponse(
            token,
            user.getEmail(),
            user.getRole().name(),
            user.getFullName()
        );
    }
}
