package com.udea.comunicacionSoporte.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioTest {

    @Test
    void testConstructorVacio() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        assertNotNull(tipoUsuario);
    }

    @Test
    void testConstructorConParametro() {
        TipoUsuario tipoUsuario = new TipoUsuario("cliente");
        assertEquals("cliente", tipoUsuario.getTipoUsuario());
    }

    @Test
    void testGettersYSetters() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        
        tipoUsuario.setIdTipoUsuario(1L);
        tipoUsuario.setTipoUsuario("administrador");

        assertEquals(1L, tipoUsuario.getIdTipoUsuario());
        assertEquals("administrador", tipoUsuario.getTipoUsuario());
    }

    @Test
    void testSetIdTipoUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(100L);
        assertEquals(100L, tipoUsuario.getIdTipoUsuario());
    }

    @Test
    void testSetTipoUsuario() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setTipoUsuario("gestor");
        assertEquals("gestor", tipoUsuario.getTipoUsuario());
    }
} 