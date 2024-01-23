package miu.edu.demoinclass.service;


import miu.edu.demoinclass.dto.LoginRequest;
import miu.edu.demoinclass.dto.LoginResponse;
import miu.edu.demoinclass.dto.RefreshTokenRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
