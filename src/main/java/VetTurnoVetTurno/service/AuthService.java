package VetTurnoVetTurno.service;

import VetTurnoVetTurno.dto.AuthResponse;
import VetTurnoVetTurno.dto.LoginRequest;
import VetTurnoVetTurno.dto.RegistroRequest;
import VetTurnoVetTurno.model.Rol;
import VetTurnoVetTurno.model.Usuario;
import VetTurnoVetTurno.repository.UsuarioRepository;
import VetTurnoVetTurno.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya se encuentra registrado.");
        }

        Usuario usuario = new Usuario(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Rol.USER
        );

        usuarioRepository.save(usuario);
        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas."));

        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }
}