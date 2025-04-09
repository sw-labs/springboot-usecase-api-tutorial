package com.bicicletas.trayectos.logica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bicicletas.trayectos.dataAccess.TrayectosRepository;
import com.bicicletas.trayectos.dataAccess.UbicacionesRepository;
import com.bicicletas.trayectos.modelo.Trayecto;
import com.bicicletas.trayectos.modelo.Ubicacion;

import jakarta.transaction.Transactional;

@SpringBootTest
class TrayectosServiceTests {

	@Autowired
	TrayectosService servicio;

	@Autowired
	TrayectosRepository trayectos;

	@Autowired
	UbicacionesRepository ubicaciones;

	@Test
	@Transactional
	void iniciarTrayecto_almacenaTrayectoYUbicacion() {

		try {

			// ejecuta el caso de uso
			UUID id = servicio.iniciarTrayecto(27.0, 42.0);

			// revisa si se almacenó el trayecto
			Optional<Trayecto> resultado = trayectos.findById(id);
			assertFalse(resultado.isEmpty(), "No se grabó el trayecto");

			Trayecto t = resultado.get();
			assertTrue(t.isEnProceso(), "No aparece como un trayecto activo");

			assertEquals(1, t.getUbicaciones().size(), "Agregó más de una ubicación al trayecto");

			Ubicacion u = t.getUbicaciones().get(0);
			assertEquals(27.0, u.getLongitud(), "La longitud en la base de datos no coincide con el parámetro");
			assertEquals(42.0, u.getLatitud(), "La latitud en la base de datos no coincide con el parámetro");

		} catch (Exception e) {
			fail("Genero excepción y no debería", e);
		}

		try {
			
			assertEquals(1, trayectos.count(), "Agregó más de un trayecto a la base de datos");		
			assertEquals(1, ubicaciones.count(), "Agregó más de una ubicación a la base de datos");

		} catch (Exception e) {
			fail("Genero excepción y no debería", e);
		}

	}

}
