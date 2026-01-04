package com.vedang.coursell.service;

import com.vedang.coursell.dto.auth.AuthResponse;
import com.vedang.coursell.dto.auth.LoginRequest;
import com.vedang.coursell.dto.auth.RegisterRequest;
import com.vedang.coursell.model.User;
import com.vedang.coursell.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.email())) {
            throw new RuntimeException("User Already Exists");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .role(request.role())
                .build();

        userRepo.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.email());
        if (user == null) {
            throw new RuntimeException("User doesn't exist");
        }

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }


}
