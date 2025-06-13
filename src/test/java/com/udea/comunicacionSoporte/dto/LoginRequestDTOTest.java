package com.udea.comunicacionSoporte.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    @Test
    void testConstructorYGetters() {
        LoginRequestDTO dto = new LoginRequestDTO("juan@udea.edu.co", "1234");
        assertEquals("juan@udea.edu.co", dto.getCorreo());
        assertEquals("1234", dto.getClave());
    }

    @Test
    void testSetters() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setCorreo("test@udea.edu.co");
        dto.setClave("abcd");

        assertEquals("test@udea.edu.co", dto.getCorreo());
        assertEquals("abcd", dto.getClave());
    }
}
