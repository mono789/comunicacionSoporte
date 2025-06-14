package com.udea.comunicacionSoporte.service;

import com.udea.comunicacionSoporte.dto.PqrsDTO;
import com.udea.comunicacionSoporte.entity.*;
import com.udea.comunicacionSoporte.exception.ResourceNotFoundException;
import com.udea.comunicacionSoporte.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PqrsServiceTest {

    @Mock private PqrsRepository pqrsRepository;
    @Mock private TipoPQRSRepository tipoPQRSRepository;
    @Mock private EstadoPQRSRepository estadoPQRSRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private EmpleadoRepository empleadoRepository;

    @InjectMocks private PqrsService pqrsService;

    private PQRS pqrs;
    private PqrsDTO pqrsDTO;
    private TipoPQRS tipo;
    private Cliente cliente;
    private Empleado empleado;
    private EstadoPQRS estado;

    @BeforeEach
    void setUp() {
        tipo = new TipoPQRS("Queja");
        tipo.setIdTipoPqrs(1L);
        
        cliente = new Cliente();
        cliente.setIdCliente(1L);
        
        empleado = new Empleado();
        empleado.setIdEmpleado(1L);
        
        estado = new EstadoPQRS("Abierto");
        estado.setIdEstadoPqrs(1L);
        
        pqrs = new PQRS(tipo, empleado, cliente, "Asunto Test", "Descripción Test", estado, LocalDateTime.now());
        pqrs.setIdPqrs(1L);
        
        pqrsDTO = new PqrsDTO();
        pqrsDTO.setIdPqrs(1L);
        pqrsDTO.setIdTipoPqrs(1L);
        pqrsDTO.setIdCliente(1L);
        pqrsDTO.setIdEmpleadoGestor(1L);
        pqrsDTO.setIdEstadoPqrs(1L);
        pqrsDTO.setAsuntoPqrs("Asunto Test");
        pqrsDTO.setDescripcionPqrs("Descripción Test");
    }

    @Test
    void testListarTodas() {
        when(pqrsRepository.findAll()).thenReturn(Arrays.asList(pqrs));
        
        List<PqrsDTO> resultado = pqrsService.listarTodas();
        
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Asunto Test", resultado.get(0).getAsuntoPqrs());
        verify(pqrsRepository).findAll();
    }

    @Test
    void testListarPorIdUsuario() {
        when(pqrsRepository.findByClienteIdCliente(1L)).thenReturn(Arrays.asList(pqrs));
        
        List<PqrsDTO> resultado = pqrsService.listarPorIdUsuario(1L);
        
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(pqrsRepository).findByClienteIdCliente(1L);
    }

    @Test
    void testObtenerPorId() {
        when(pqrsRepository.findById(1L)).thenReturn(Optional.of(pqrs));
        
        PqrsDTO resultado = pqrsService.obtenerPorId(1L);
        
        assertNotNull(resultado);
        assertEquals("Asunto Test", resultado.getAsuntoPqrs());
        verify(pqrsRepository).findById(1L);
    }

    @Test
    void testObtenerPorIdNoEncontrado() {
        when(pqrsRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> pqrsService.obtenerPorId(1L));
        verify(pqrsRepository).findById(1L);
    }

    @Test
    void testCrear() {
        when(tipoPQRSRepository.findById(1L)).thenReturn(Optional.of(tipo));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(estadoPQRSRepository.findById(1L)).thenReturn(Optional.of(estado));
        when(pqrsRepository.save(any(PQRS.class))).thenReturn(pqrs);
        
        PqrsDTO resultado = pqrsService.crear(pqrsDTO);
        
        assertNotNull(resultado);
        assertEquals("Asunto Test", resultado.getAsuntoPqrs());
        verify(pqrsRepository).save(any(PQRS.class));
    }

    @Test
    void testCrearTipoNoEncontrado() {
        when(tipoPQRSRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> pqrsService.crear(pqrsDTO));
        verify(tipoPQRSRepository).findById(1L);
    }

    @Test
    void testCrearClienteNoEncontrado() {
        when(tipoPQRSRepository.findById(1L)).thenReturn(Optional.of(tipo));
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> pqrsService.crear(pqrsDTO));
    }

    @Test
    void testActualizar() {
        when(pqrsRepository.findById(1L)).thenReturn(Optional.of(pqrs));
        when(tipoPQRSRepository.findById(1L)).thenReturn(Optional.of(tipo));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(estadoPQRSRepository.findById(1L)).thenReturn(Optional.of(estado));
        when(pqrsRepository.save(any(PQRS.class))).thenReturn(pqrs);
        
        PqrsDTO resultado = pqrsService.actualizar(1L, pqrsDTO);
        
        assertNotNull(resultado);
        verify(pqrsRepository).save(any(PQRS.class));
    }

    @Test
    void testActualizarNoEncontrado() {
        when(pqrsRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> pqrsService.actualizar(1L, pqrsDTO));
    }

    @Test
    void testEliminar() {
        when(pqrsRepository.existsById(1L)).thenReturn(true);
        
        assertDoesNotThrow(() -> pqrsService.eliminar(1L));
        verify(pqrsRepository).deleteById(1L);
    }

    @Test
    void testEliminarNoEncontrado() {
        when(pqrsRepository.existsById(1L)).thenReturn(false);
        
        assertThrows(ResourceNotFoundException.class, () -> pqrsService.eliminar(1L));
        verify(pqrsRepository, never()).deleteById(1L);
    }
} 