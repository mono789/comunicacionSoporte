package com.udea.comunicacionSoporte.controller;

import com.udea.comunicacionSoporte.dto.LoginRequestDTO;
import com.udea.comunicacionSoporte.dto.LoginResponseDTO;
import com.udea.comunicacionSoporte.dto.RegistroUsuarioDTO;
import com.udea.comunicacionSoporte.entity.Usuario;
import com.udea.comunicacionSoporte.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private RegistroUsuarioDTO registroDto;
    private LoginRequestDTO loginDto;
    private Usuario usuario;
    private LoginResponseDTO loginResponse;

    @BeforeEach
    void setUp() {
        registroDto = new RegistroUsuarioDTO("test@test.com", "password123", "cliente");
        loginDto = new LoginRequestDTO("test@test.com", "password123");
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setCorreoUsuario("test@test.com");
        
        loginResponse = new LoginResponseDTO("token123", "Login exitoso");
    }

    @Test
    void testRegistrar() {
        when(usuarioService.registrarUsuario(registroDto)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.registrar(registroDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(usuario, response.getBody());
        verify(usuarioService).registrarUsuario(registroDto);
    }

    @Test
    void testLogin() {
        when(usuarioService.login(loginDto)).thenReturn(loginResponse);

        ResponseEntity<?> response = usuarioController.login(loginDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(loginResponse, response.getBody());
        verify(usuarioService).login(loginDto);
    }
} 