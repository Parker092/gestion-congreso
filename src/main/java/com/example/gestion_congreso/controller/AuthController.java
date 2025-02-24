package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.dto.AuthRequest;
import com.example.gestion_congreso.model.Usuario;
import com.example.gestion_congreso.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticación", description = "Endpoints para autenticación y gestión de usuarios")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y devuelve un token JWT.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            String token = authService.login(authRequest.getUsername(), authRequest.getPassword());
            return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @Operation(summary = "Registrar un usuario", description = "Registra un nuevo usuario en la base de datos.")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        usuario.setId(null); // ✅ Evitar que el cliente pase un ID manualmente

        if (usuario.getUsername() == null || usuario.getUsername().isEmpty() ||
                usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario y la contraseña son obligatorios");
        }

        return ResponseEntity.ok(authService.register(usuario));
    }
}