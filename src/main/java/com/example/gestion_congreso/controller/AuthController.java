package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.dto.AuthRequest;
import com.example.gestion_congreso.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
