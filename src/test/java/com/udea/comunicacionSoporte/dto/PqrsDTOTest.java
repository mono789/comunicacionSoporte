package com.udea.comunicacionSoporte.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PqrsDTOTest {

    @Test
    void testSettersYGetters() {
        PqrsDTO dto = new PqrsDTO();
        LocalDateTime now = LocalDateTime.now();

        dto.setIdPqrs(1L);
        dto.setIdTipoPqrs(2L);
        dto.setIdEmpleadoGestor(3L);
        dto.setIdCliente(4L);
        dto.setAsuntoPqrs("Asunto");
        dto.setDescripcionPqrs("Descripción");
        dto.setIdEstadoPqrs(5L);
        dto.setFechaRadicacion(now);
        dto.setFechaModificacion(now.plusDays(1));
        dto.setFechaExpiracion(now.plusDays(5));

        assertEquals(1L, dto.getIdPqrs());
        assertEquals(2L, dto.getIdTipoPqrs());
        assertEquals(3L, dto.getIdEmpleadoGestor());
        assertEquals(4L, dto.getIdCliente());
        assertEquals("Asunto", dto.getAsuntoPqrs());
        assertEquals("Descripción", dto.getDescripcionPqrs());
        assertEquals(5L, dto.getIdEstadoPqrs());
        assertEquals(now, dto.getFechaRadicacion());
        assertEquals(now.plusDays(1), dto.getFechaModificacion());
        assertEquals(now.plusDays(5), dto.getFechaExpiracion());
    }
}
