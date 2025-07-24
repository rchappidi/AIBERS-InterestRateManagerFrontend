package com.irm.service;

import com.irm.dto.LoginRequest;
import com.irm.dto.LoginResponse;
import com.irm.model.User;
import com.irm.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = authRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        // User implements UserDetails
        String token = jwtService.generateToken(extraClaims,user);
        return new LoginResponse(token);
    }
}
