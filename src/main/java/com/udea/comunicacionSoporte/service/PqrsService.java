package com.udea.comunicacionSoporte.service;

import com.udea.comunicacionSoporte.dto.PqrsDTO;
import com.udea.comunicacionSoporte.entity.*;
import com.udea.comunicacionSoporte.exception.ResourceNotFoundException;
import com.udea.comunicacionSoporte.mapper.PqrsMapper;
import com.udea.comunicacionSoporte.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PqrsService {

    private final PqrsRepository pqrsRepository;
    private final TipoPQRSRepository tipoPQRSRepository;
    private final EstadoPQRSRepository estadoPQRSRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    public PqrsService(PqrsRepository pqrsRepository,
                       TipoPQRSRepository tipoPQRSRepository,
                       EstadoPQRSRepository estadoPQRSRepository,
                       ClienteRepository clienteRepository,
                       EmpleadoRepository empleadoRepository) {
        this.pqrsRepository = pqrsRepository;
        this.tipoPQRSRepository = tipoPQRSRepository;
        this.estadoPQRSRepository = estadoPQRSRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public List<PqrsDTO> listarTodas() {
        return pqrsRepository.findAll().stream()
                .map(PqrsMapper::toDTO)
                .toList();
    }

    public List<PqrsDTO> listarPorIdUsuario(Long id) {
        return pqrsRepository.findByClienteIdCliente(id).stream()
                .map(PqrsMapper::toDTO)
                .toList();
    }

    public PqrsDTO obtenerPorId(Long id) {
        return pqrsRepository.findById(id)
                .map(PqrsMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("PQRS no encontrada con ID: " + id));
    }

    public PqrsDTO crear(PqrsDTO dto) {
        TipoPQRS tipo = tipoPQRSRepository.findById(dto.getIdTipoPqrs())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo PQRS no encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        Empleado gestor = empleadoRepository.findById(dto.getIdEmpleadoGestor())
                .orElseThrow(() -> new ResourceNotFoundException("Gestor no encontrado"));
        EstadoPQRS estado = estadoPQRSRepository.findById(dto.getIdEstadoPqrs())
                .orElseThrow(() -> new ResourceNotFoundException("Estado PQRS no encontrado"));

        LocalDateTime fechaRadicacion = LocalDateTime.now();
        dto.setFechaRadicacion(fechaRadicacion);
        PQRS pqrs = PqrsMapper.toEntity(dto, tipo, gestor, cliente, estado);
        PQRS guardado = pqrsRepository.save(pqrs);
        return PqrsMapper.toDTO(guardado);
    }

    public PqrsDTO actualizar(Long id, PqrsDTO dto) {
        PQRS existente = pqrsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PQRS no encontrada con ID: " + id));

        TipoPQRS tipo = tipoPQRSRepository.findById(dto.getIdTipoPqrs())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo PQRS no encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        Empleado gestor = empleadoRepository.findById(dto.getIdEmpleadoGestor())
                .orElseThrow(() -> new ResourceNotFoundException("Gestor no encontrado"));
        EstadoPQRS estado = estadoPQRSRepository.findById(dto.getIdEstadoPqrs())
                .orElseThrow(() -> new ResourceNotFoundException("Estado PQRS no encontrado"));

        existente.setTipoPqrs(tipo);
        existente.setCliente(cliente);
        existente.setEmpleadoGestor(gestor);
        existente.setEstadoPqrs(estado);
        existente.setAsuntoPqrs(dto.getAsuntoPqrs());
        existente.setDescripcionPqrs(dto.getDescripcionPqrs());
        existente.setFechaModificacion(dto.getFechaModificacion());
        existente.setFechaExpiracion(dto.getFechaExpiracion());

        PQRS actualizado = pqrsRepository.save(existente);
        return PqrsMapper.toDTO(actualizado);
    }

    public void eliminar(Long id) {
        if (!pqrsRepository.existsById(id)) {
            throw new ResourceNotFoundException("PQRS no encontrada con ID: " + id);
        }
        pqrsRepository.deleteById(id);
    }
}
