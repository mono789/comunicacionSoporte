package com.udea.comunicacionSoporte.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseDTOTest {

    @Test
    void testConstructoresYGettersSetters() {
        LoginResponseDTO dto = new LoginResponseDTO();
        dto.setToken("abc123");
        dto.setMensaje("Inicio de sesión exitoso");

        assertEquals("abc123", dto.getToken());
        assertEquals("Inicio de sesión exitoso", dto.getMensaje());

        LoginResponseDTO dto2 = new LoginResponseDTO("xyz789", "Token generado");
        assertEquals("xyz789", dto2.getToken());
        assertEquals("Token generado", dto2.getMensaje());
    }
}
