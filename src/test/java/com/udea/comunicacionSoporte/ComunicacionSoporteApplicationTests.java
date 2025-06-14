package com.udea.comunicacionSoporte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComunicacionSoporteApplicationTests {

	@Test
	void testApplicationClassIsValid() {
		assertNotNull(ComunicacionSoporteApplication.class);
		assertTrue(ComunicacionSoporteApplication.class.isAnnotationPresent(
			org.springframework.boot.autoconfigure.SpringBootApplication.class));
	}
}
