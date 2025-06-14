package com.udea.comunicacionSoporte.controller;

import com.udea.comunicacionSoporte.dto.PqrsDTO;
import com.udea.comunicacionSoporte.service.PqrsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pqrs")
@CrossOrigin(origins = "*") // Ajusta si usas frontend separado
public class PqrsController {

    private final PqrsService pqrsService;

    public PqrsController(PqrsService pqrsService) {
        this.pqrsService = pqrsService;
    }

    @GetMapping
    public ResponseEntity<List<PqrsDTO>> listarTodas() {
        return ResponseEntity.ok(pqrsService.listarTodas());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<PqrsDTO>> listarPorIdUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(pqrsService.listarPorIdUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PqrsDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pqrsService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PqrsDTO> crear(@RequestBody PqrsDTO dto) {
        return ResponseEntity.ok(pqrsService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PqrsDTO> actualizar(@PathVariable Long id, @RequestBody PqrsDTO dto) {
        return ResponseEntity.ok(pqrsService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pqrsService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
