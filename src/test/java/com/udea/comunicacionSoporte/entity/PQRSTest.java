package com.udea.comunicacionSoporte.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class PQRSTest {

    @Test
    void testConstructorYGetters() {
        TipoPQRS tipo = new TipoPQRS();
        Empleado gestor = new Empleado();
        Cliente cliente = new Cliente();
        EstadoPQRS estado = new EstadoPQRS();
        LocalDateTime fecha = LocalDateTime.now();

        PQRS pqrs = new PQRS(tipo, gestor, cliente, "Asunto prueba", "Descripción prueba", estado, fecha);

        assertEquals(tipo, pqrs.getTipoPqrs());
        assertEquals(gestor, pqrs.getEmpleadoGestor());
        assertEquals(cliente, pqrs.getCliente());
        assertEquals("Asunto prueba", pqrs.getAsuntoPqrs());
        assertEquals("Descripción prueba", pqrs.getDescripcionPqrs());
        assertEquals(estado, pqrs.getEstadoPqrs());
        assertEquals(fecha, pqrs.getFechaRadicacion());
    }

    @Test
    void testSetters() {
        PQRS pqrs = new PQRS();
        TipoPQRS tipo = new TipoPQRS();
        Empleado gestor = new Empleado();
        Cliente cliente = new Cliente();
        EstadoPQRS estado = new EstadoPQRS();
        LocalDateTime fecha = LocalDateTime.now();

        pqrs.setTipoPqrs(tipo);
        pqrs.setEmpleadoGestor(gestor);
        pqrs.setCliente(cliente);
        pqrs.setAsuntoPqrs("Nuevo Asunto");
        pqrs.setDescripcionPqrs("Nueva Descripción");
        pqrs.setEstadoPqrs(estado);
        pqrs.setFechaRadicacion(fecha);
        pqrs.setFechaModificacion(fecha.plusDays(1));
        pqrs.setFechaExpiracion(fecha.plusDays(30));

        assertEquals(tipo, pqrs.getTipoPqrs());
        assertEquals(gestor, pqrs.getEmpleadoGestor());
        assertEquals(cliente, pqrs.getCliente());
        assertEquals("Nuevo Asunto", pqrs.getAsuntoPqrs());
        assertEquals("Nueva Descripción", pqrs.getDescripcionPqrs());
        assertEquals(estado, pqrs.getEstadoPqrs());
        assertEquals(fecha, pqrs.getFechaRadicacion());
        assertEquals(fecha.plusDays(1), pqrs.getFechaModificacion());
        assertEquals(fecha.plusDays(30), pqrs.getFechaExpiracion());
    }
}
