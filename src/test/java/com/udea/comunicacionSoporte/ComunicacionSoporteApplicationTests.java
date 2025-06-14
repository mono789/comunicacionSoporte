package com.udea.comunicacionSoporte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComunicacionSoporteApplicationTests {

	@Test
	void testApplicationClassExists() {
		// Verifica que la clase principal existe y es instanciable
		assertDoesNotThrow(() -> {
			ComunicacionSoporteApplication app = new ComunicacionSoporteApplication();
			assertNotNull(app);
		});
	}

	@Test
	void testMainMethodExists() {
		// Verifica que el método main existe y es accesible
		assertDoesNotThrow(() -> {
			// Obtener el método main usando reflexión
			var mainMethod = ComunicacionSoporteApplication.class.getMethod("main", String[].class);
			assertNotNull(mainMethod);
			assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
			assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
		});
	}

	@Test
	void testSpringBootApplicationAnnotation() {
		// Verifica que la clase tiene la anotación @SpringBootApplication
		assertTrue(ComunicacionSoporteApplication.class.isAnnotationPresent(
			org.springframework.boot.autoconfigure.SpringBootApplication.class));
	}
}
