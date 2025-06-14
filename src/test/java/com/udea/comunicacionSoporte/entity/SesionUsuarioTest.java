package com.udea.comunicacionSoporte.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SesionUsuarioTest {

    private SesionUsuario sesionUsuario;
    private Usuario usuario;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaExpiracion;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setCorreoUsuario("test@test.com");
        
        fechaInicio = LocalDateTime.now();
        fechaExpiracion = fechaInicio.plusHours(1);
        
        sesionUsuario = new SesionUsuario();
    }

    @Test
    void testConstructorVacio() {
        SesionUsuario sesion = new SesionUsuario();
        assertNotNull(sesion);
    }

    @Test
    void testConstructorConParametros() {
        SesionUsuario sesion = new SesionUsuario(usuario, "token123", fechaInicio, fechaExpiracion, true);
        
        assertEquals(usuario, sesion.getUsuario());
        assertEquals("token123", sesion.getTokenSesion());
        assertEquals(fechaInicio, sesion.getFechaInicio());
        assertEquals(fechaExpiracion, sesion.getFechaExpiracion());
        assertTrue(sesion.getActivo());
    }

    @Test
    void testGettersYSetters() {
        sesionUsuario.setIdSesion(1L);
        sesionUsuario.setUsuario(usuario);
        sesionUsuario.setTokenSesion("abc123token");
        sesionUsuario.setFechaInicio(fechaInicio);
        sesionUsuario.setFechaExpiracion(fechaExpiracion);
        sesionUsuario.setActivo(true);

        assertEquals(1L, sesionUsuario.getIdSesion());
        assertEquals(usuario, sesionUsuario.getUsuario());
        assertEquals("abc123token", sesionUsuario.getTokenSesion());
        assertEquals(fechaInicio, sesionUsuario.getFechaInicio());
        assertEquals(fechaExpiracion, sesionUsuario.getFechaExpiracion());
        assertTrue(sesionUsuario.getActivo());
    }

    @Test
    void testSetIdSesion() {
        sesionUsuario.setIdSesion(100L);
        assertEquals(100L, sesionUsuario.getIdSesion());
    }

    @Test
    void testSetUsuario() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setIdUsuario(2L);
        sesionUsuario.setUsuario(nuevoUsuario);
        assertEquals(nuevoUsuario, sesionUsuario.getUsuario());
    }

    @Test
    void testSetTokenSesion() {
        sesionUsuario.setTokenSesion("nuevoToken456");
        assertEquals("nuevoToken456", sesionUsuario.getTokenSesion());
    }

    @Test
    void testSetFechaInicio() {
        LocalDateTime nuevaFecha = LocalDateTime.of(2023, 1, 1, 10, 0);
        sesionUsuario.setFechaInicio(nuevaFecha);
        assertEquals(nuevaFecha, sesionUsuario.getFechaInicio());
    }

    @Test
    void testSetFechaExpiracion() {
        LocalDateTime nuevaExpiracion = LocalDateTime.of(2023, 1, 1, 11, 0);
        sesionUsuario.setFechaExpiracion(nuevaExpiracion);
        assertEquals(nuevaExpiracion, sesionUsuario.getFechaExpiracion());
    }

    @Test
    void testSetActivo() {
        sesionUsuario.setActivo(false);
        assertFalse(sesionUsuario.getActivo());
        
        sesionUsuario.setActivo(true);
        assertTrue(sesionUsuario.getActivo());
    }
} 