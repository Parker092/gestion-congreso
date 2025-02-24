package com.example.gestion_congreso.service;

import com.example.gestion_congreso.model.Rol;
import com.example.gestion_congreso.model.Usuario;
import com.example.gestion_congreso.repository.UserRepository;
import com.example.gestion_congreso.security.JwtUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final Argon2 argon2; // ✅ Crear una instancia persistente

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.argon2 = Argon2Factory.create(); // ✅ Instanciar una vez
    }

    public String login(String username, String password) {
        Usuario usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        // ✅ Validar que el hash en BD sea Argon2
        if (!usuario.getPassword().startsWith("$argon2")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Formato de contraseña no soportado");
        }

        // ✅ Verificar la contraseña con Argon2
        if (!argon2.verify(usuario.getPassword(), password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        return jwtUtil.generateToken(usuario.getUsername(), usuario.getRol().name());
    }

    @Transactional
    public Usuario register(Usuario usuario) {
        usuario.setId(null); // ✅ Evitar que el cliente pase un ID manualmente

        if (usuario.getUsername() == null || usuario.getUsername().isEmpty() ||
                usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario y la contraseña son obligatorios");
        }

        if (userRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya está registrado");
        }

        // ✅ Encriptar la contraseña con Argon2 correctamente
        String hashedPassword = argon2.hash(2, 16 * 1024, 1, usuario.getPassword());
        usuario.setPassword(hashedPassword);

        if (usuario.getRol() == null) {
            usuario.setRol(Rol.USER);
        }

        return userRepository.save(usuario);
    }
}