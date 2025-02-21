package com.example.gestion_congreso.service;

import com.example.gestion_congreso.dto.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public ResponseEntity<String> authenticate(AuthRequest request) {
        // Aquí puedes validar el usuario con base de datos o credenciales estáticas
        if ("admin@example.com".equals(request.getEmail()) && "password123".equals(request.getPassword())) {
            return ResponseEntity.ok("Token de autenticación generado correctamente");
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}
