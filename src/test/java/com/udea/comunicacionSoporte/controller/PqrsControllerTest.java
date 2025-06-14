package com.udea.comunicacionSoporte.controller;

import com.udea.comunicacionSoporte.dto.PqrsDTO;
import com.udea.comunicacionSoporte.service.PqrsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PqrsControllerTest {

    @Mock
    private PqrsService pqrsService;

    @InjectMocks
    private PqrsController pqrsController;

    private PqrsDTO pqrsDTO;
    private List<PqrsDTO> pqrsList;

    @BeforeEach
    void setUp() {
        pqrsDTO = new PqrsDTO();
        pqrsDTO.setIdPqrs(1L);
        pqrsDTO.setAsuntoPqrs("Test Asunto");
        pqrsDTO.setDescripcionPqrs("Test Descripción");
        pqrsDTO.setFechaRadicacion(LocalDateTime.now());

        pqrsList = Arrays.asList(pqrsDTO);
    }

    @Test
    void testListarTodas() {
        when(pqrsService.listarTodas()).thenReturn(pqrsList);

        ResponseEntity<List<PqrsDTO>> response = pqrsController.listarTodas();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(pqrsList, response.getBody());
        assertEquals(1, response.getBody().size());
        verify(pqrsService).listarTodas();
    }

    @Test
    void testListarPorIdUsuario() {
        Long userId = 1L;
        when(pqrsService.listarPorIdUsuario(userId)).thenReturn(pqrsList);

        ResponseEntity<List<PqrsDTO>> response = pqrsController.listarPorIdUsuario(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(pqrsList, response.getBody());
        verify(pqrsService).listarPorIdUsuario(userId);
    }

    @Test
    void testObtenerPorId() {
        Long pqrsId = 1L;
        when(pqrsService.obtenerPorId(pqrsId)).thenReturn(pqrsDTO);

        ResponseEntity<PqrsDTO> response = pqrsController.obtenerPorId(pqrsId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(pqrsDTO, response.getBody());
        verify(pqrsService).obtenerPorId(pqrsId);
    }

    @Test
    void testCrear() {
        when(pqrsService.crear(pqrsDTO)).thenReturn(pqrsDTO);

        ResponseEntity<PqrsDTO> response = pqrsController.crear(pqrsDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(pqrsDTO, response.getBody());
        verify(pqrsService).crear(pqrsDTO);
    }

    @Test
    void testActualizar() {
        Long pqrsId = 1L;
        when(pqrsService.actualizar(pqrsId, pqrsDTO)).thenReturn(pqrsDTO);

        ResponseEntity<PqrsDTO> response = pqrsController.actualizar(pqrsId, pqrsDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(pqrsDTO, response.getBody());
        verify(pqrsService).actualizar(pqrsId, pqrsDTO);
    }

    @Test
    void testEliminar() {
        Long pqrsId = 1L;
        doNothing().when(pqrsService).eliminar(pqrsId);

        ResponseEntity<Void> response = pqrsController.eliminar(pqrsId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(pqrsService).eliminar(pqrsId);
    }
} 