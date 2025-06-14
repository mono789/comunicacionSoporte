package com.udea.comunicacionSoporte.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario usuario;
    private TipoUsuario tipoUsuario;
    private LocalDateTime fechaRegistro;

    @BeforeEach
    void setUp() {
        tipoUsuario = new TipoUsuario("cliente");
        fechaRegistro = LocalDateTime.now();
        usuario = new Usuario();
    }

    @Test
    void testConstructorVacio() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario);
    }

    @Test
    void testConstructorConParametros() {
        Usuario usuario = new Usuario("test@test.com", "hashedPassword", fechaRegistro, tipoUsuario);
        
        assertEquals("test@test.com", usuario.getCorreoUsuario());
        assertEquals("hashedPassword", usuario.getClaveHash());
        assertEquals(fechaRegistro, usuario.getFechaRegistro());
        assertEquals(tipoUsuario, usuario.getTipoUsuario());
    }

    @Test
    void testGettersYSetters() {
        usuario.setIdUsuario(1L);
        usuario.setCorreoUsuario("test@ejemplo.com");
        usuario.setClaveHash("passwordHashed");
        usuario.setFechaRegistro(fechaRegistro);
        usuario.setTipoUsuario(tipoUsuario);

        assertEquals(1L, usuario.getIdUsuario());
        assertEquals("test@ejemplo.com", usuario.getCorreoUsuario());
        assertEquals("passwordHashed", usuario.getClaveHash());
        assertEquals(fechaRegistro, usuario.getFechaRegistro());
        assertEquals(tipoUsuario, usuario.getTipoUsuario());
    }

    @Test
    void testSetIdUsuario() {
        usuario.setIdUsuario(100L);
        assertEquals(100L, usuario.getIdUsuario());
    }

    @Test
    void testSetCorreoUsuario() {
        usuario.setCorreoUsuario("nuevo@correo.com");
        assertEquals("nuevo@correo.com", usuario.getCorreoUsuario());
    }

    @Test
    void testSetClaveHash() {
        usuario.setClaveHash("nuevaClaveHash");
        assertEquals("nuevaClaveHash", usuario.getClaveHash());
    }

    @Test
    void testSetFechaRegistro() {
        LocalDateTime nuevaFecha = LocalDateTime.of(2023, 1, 1, 10, 0);
        usuario.setFechaRegistro(nuevaFecha);
        assertEquals(nuevaFecha, usuario.getFechaRegistro());
    }

    @Test
    void testSetTipoUsuario() {
        TipoUsuario nuevoTipo = new TipoUsuario("administrador");
        usuario.setTipoUsuario(nuevoTipo);
        assertEquals(nuevoTipo, usuario.getTipoUsuario());
    }
} 