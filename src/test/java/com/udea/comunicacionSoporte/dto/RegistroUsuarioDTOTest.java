package com.udea.comunicacionSoporte.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistroUsuarioDTOTest {

    @Test
    void testConstructoresYGettersSetters() {
        RegistroUsuarioDTO dto = new RegistroUsuarioDTO();
        dto.setCorreo("correo@ejemplo.com");
        dto.setClave("123456");
        dto.setTipoUsuario("cliente");

        assertEquals("correo@ejemplo.com", dto.getCorreo());
        assertEquals("123456", dto.getClave());
        assertEquals("cliente", dto.getTipoUsuario());

        RegistroUsuarioDTO dto2 = new RegistroUsuarioDTO("otro@correo.com", "abcdef", "gestor");
        assertEquals("otro@correo.com", dto2.getCorreo());
        assertEquals("abcdef", dto2.getClave());
        assertEquals("gestor", dto2.getTipoUsuario());
    }
}
