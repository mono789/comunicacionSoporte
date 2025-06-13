package com.udea.comunicacionSoporte.service;

import com.udea.comunicacionSoporte.dto.*;
import com.udea.comunicacionSoporte.entity.*;
import com.udea.comunicacionSoporte.repository.*;
import com.udea.comunicacionSoporte.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepo;
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepo;
    @Mock
    private SesionUsuarioRepository sesionUsuarioRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuarioOk() {
        RegistroUsuarioDTO dto = new RegistroUsuarioDTO("correo@test.com", "clave123", "cliente");

        when(usuarioRepo.existsByCorreoUsuario(dto.getCorreo())).thenReturn(false);
        when(tipoUsuarioRepo.findByTipoUsuario("cliente")).thenReturn(Optional.of(new TipoUsuario()));
        when(passwordEncoder.encode("clave123")).thenReturn("hash123");
        when(usuarioRepo.save(any())).thenReturn(new Usuario());

        Usuario u = usuarioService.registrarUsuario(dto);
        assertNotNull(u);
    }

    @Test
    void testLoginCorrecto() {
        LoginRequestDTO req = new LoginRequestDTO("test@test.com", "clave");

        Usuario usuario = new Usuario();
        usuario.setClaveHash("hashed");
        when(usuarioRepo.findByCorreoUsuario("test@test.com")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("clave", "hashed")).thenReturn(true);
        when(jwtTokenProvider.generarToken(usuario)).thenReturn("token123");

        LoginResponseDTO res = usuarioService.login(req);
        assertEquals("token123", res.getToken());
    }

    @Test
    void testLoginUsuarioNoExiste() {
        when(usuarioRepo.findByCorreoUsuario("x@x.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> usuarioService.login(new LoginRequestDTO("x@x.com", "clave")));
    }

    @Test
    void testLoginClaveIncorrecta() {
        Usuario u = new Usuario();
        u.setClaveHash("hash");

        when(usuarioRepo.findByCorreoUsuario("a@a.com")).thenReturn(Optional.of(u));
        when(passwordEncoder.matches("clave", "hash")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> usuarioService.login(new LoginRequestDTO("a@a.com", "clave")));
    }
}
