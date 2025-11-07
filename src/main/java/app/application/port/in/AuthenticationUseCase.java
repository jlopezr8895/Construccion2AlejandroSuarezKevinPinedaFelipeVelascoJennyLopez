package app.application.port.in;

import app.infrastructure.adapter.api.rest.dto.auth.LoginRequest;
import app.infrastructure.adapter.api.rest.dto.auth.LoginResponse;

public interface AuthenticationUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
