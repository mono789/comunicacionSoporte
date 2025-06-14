package com.udea.comunicacionSoporte.security;

import com.udea.comunicacionSoporte.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;
    private Usuario usuario;
    private String testSecret = "dGVzdC1zZWNyZXQtZm9yLWp3dC10b2tlbi1nZW5lcmF0aW9uLXB1cnBvc2VzLXRlc3QtY29kZS1jb3ZlcmFnZQ==";

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(testSecret);
        
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setCorreoUsuario("test@test.com");
    }

    @Test
    void testGenerarToken() {
        String token = jwtTokenProvider.generarToken(usuario);
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.contains("."));
    }

    @Test
    void testObtenerCorreoDesdeToken() {
        String token = jwtTokenProvider.generarToken(usuario);
        
        String correo = jwtTokenProvider.obtenerCorreoDesdeToken(token);
        
        assertEquals("test@test.com", correo);
    }

    @Test
    void testEsValidoTokenValido() {
        String token = jwtTokenProvider.generarToken(usuario);
        
        boolean esValido = jwtTokenProvider.esValido(token);
        
        assertTrue(esValido);
    }

    @Test
    void testEsValidoTokenInvalido() {
        String tokenInvalido = "token.invalido.aqui";
        
        boolean esValido = jwtTokenProvider.esValido(tokenInvalido);
        
        assertFalse(esValido);
    }

    @Test
    void testEsValidoTokenVacio() {
        boolean esValido = jwtTokenProvider.esValido("");
        
        assertFalse(esValido);
    }

    @Test
    void testEsValidoTokenNull() {
        boolean esValido = jwtTokenProvider.esValido(null);
        
        assertFalse(esValido);
    }

    @Test
    void testGenerarYValidarTokenCompleto() {
        // Generar token
        String token = jwtTokenProvider.generarToken(usuario);
        
        // Validar token
        assertTrue(jwtTokenProvider.esValido(token));
        
        // Extraer correo
        String correoExtraido = jwtTokenProvider.obtenerCorreoDesdeToken(token);
        assertEquals(usuario.getCorreoUsuario(), correoExtraido);
    }
} 