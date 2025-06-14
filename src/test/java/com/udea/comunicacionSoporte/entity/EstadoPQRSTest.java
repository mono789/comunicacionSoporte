package com.udea.comunicacionSoporte.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoPQRSTest {

    @Test
    void testConstructorVacio() {
        EstadoPQRS estado = new EstadoPQRS();
        assertNotNull(estado);
    }

    @Test
    void testConstructorConParametro() {
        EstadoPQRS estado = new EstadoPQRS("Abierto");
        assertEquals("Abierto", estado.getEstadoPqrs());
    }

    @Test
    void testGettersYSetters() {
        EstadoPQRS estado = new EstadoPQRS();
        
        estado.setIdEstadoPqrs(1L);
        estado.setEstadoPqrs("Cerrado");

        assertEquals(1L, estado.getIdEstadoPqrs());
        assertEquals("Cerrado", estado.getEstadoPqrs());
    }

    @Test
    void testSetIdEstadoPqrs() {
        EstadoPQRS estado = new EstadoPQRS();
        estado.setIdEstadoPqrs(100L);
        assertEquals(100L, estado.getIdEstadoPqrs());
    }

    @Test
    void testSetEstadoPqrs() {
        EstadoPQRS estado = new EstadoPQRS();
        estado.setEstadoPqrs("En Proceso");
        assertEquals("En Proceso", estado.getEstadoPqrs());
    }
} 