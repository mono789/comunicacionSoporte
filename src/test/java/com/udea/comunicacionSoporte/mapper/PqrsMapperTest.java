package com.udea.comunicacionSoporte.mapper;

import com.udea.comunicacionSoporte.dto.PqrsDTO;
import com.udea.comunicacionSoporte.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PqrsMapperTest {

    @Test
    void testToDTO() {
        // Arrange
        TipoPQRS tipo = new TipoPQRS();
        tipo.setIdTipoPqrs(1L);

        Empleado gestor = new Empleado();
        gestor.setIdEmpleado(2L);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(3L);

        EstadoPQRS estado = new EstadoPQRS();
        estado.setIdEstadoPqrs(4L);

        LocalDateTime now = LocalDateTime.now();

        PQRS pqrs = new PQRS();
        pqrs.setIdPqrs(10L);
        pqrs.setTipoPqrs(tipo);
        pqrs.setEmpleadoGestor(gestor);
        pqrs.setCliente(cliente);
        pqrs.setAsuntoPqrs("Asunto");
        pqrs.setDescripcionPqrs("Descripción");
        pqrs.setEstadoPqrs(estado);
        pqrs.setFechaRadicacion(now);
        pqrs.setFechaModificacion(now);
        pqrs.setFechaExpiracion(now.plusDays(5));

        // Act
        PqrsDTO dto = PqrsMapper.toDTO(pqrs);

        // Assert
        assertEquals(10L, dto.getIdPqrs());
        assertEquals(1L, dto.getIdTipoPqrs());
        assertEquals(2L, dto.getIdEmpleadoGestor());
        assertEquals(3L, dto.getIdCliente());
        assertEquals("Asunto", dto.getAsuntoPqrs());
        assertEquals("Descripción", dto.getDescripcionPqrs());
        assertEquals(4L, dto.getIdEstadoPqrs());
        assertEquals(now, dto.getFechaRadicacion());
        assertEquals(now, dto.getFechaModificacion());
        assertEquals(now.plusDays(5), dto.getFechaExpiracion());
    }

    @Test
    void testToEntity() {
        // Arrange
        PqrsDTO dto = new PqrsDTO();
        dto.setIdPqrs(11L);
        dto.setAsuntoPqrs("Otro asunto");
        dto.setDescripcionPqrs("Otra descripción");
        LocalDateTime now = LocalDateTime.now();
        dto.setFechaRadicacion(now);
        dto.setFechaModificacion(now);
        dto.setFechaExpiracion(now.plusDays(2));

        TipoPQRS tipo = new TipoPQRS();
        tipo.setIdTipoPqrs(1L);

        Empleado gestor = new Empleado();
        gestor.setIdEmpleado(2L);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(3L);

        EstadoPQRS estado = new EstadoPQRS();
        estado.setIdEstadoPqrs(4L);

        // Act
        PQRS pqrs = PqrsMapper.toEntity(dto, tipo, gestor, cliente, estado);

        // Assert
        assertEquals(11L, pqrs.getIdPqrs());
        assertEquals(tipo, pqrs.getTipoPqrs());
        assertEquals(gestor, pqrs.getEmpleadoGestor());
        assertEquals(cliente, pqrs.getCliente());
        assertEquals("Otro asunto", pqrs.getAsuntoPqrs());
        assertEquals("Otra descripción", pqrs.getDescripcionPqrs());
        assertEquals(estado, pqrs.getEstadoPqrs());
        assertEquals(now, pqrs.getFechaRadicacion());
        assertEquals(now, pqrs.getFechaModificacion());
        assertEquals(now.plusDays(2), pqrs.getFechaExpiracion());
    }
}
