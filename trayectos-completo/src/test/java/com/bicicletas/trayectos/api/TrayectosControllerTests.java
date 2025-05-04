package com.bicicletas.trayectos.api;

import com.bicicletas.trayectos.TrayectosApplication;
import com.bicicletas.trayectos.dataAccess.TrayectosRepository;
import com.bicicletas.trayectos.dataAccess.UbicacionesRepository;
import com.bicicletas.trayectos.modelo.Trayecto;
import com.bicicletas.trayectos.modelo.Ubicacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(classes = TrayectosApplication.class)
@AutoConfigureMockMvc
@Transactional
public class TrayectosControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrayectosRepository trayectosRepository;

    @Autowired
    private UbicacionesRepository ubicacionesRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"));


    @BeforeEach
    public void setUp() {
        trayectosRepository.deleteAll();
        ubicacionesRepository.deleteAll();
    }

    private String toJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void testGetTrayectos() throws Exception {
        // Arrange
        Trayecto trayecto = new Trayecto();
        trayecto.setFechaHoraInicio(LocalDateTime.now());
        trayecto.setEnProceso(true);
        trayecto.setUbicaciones(new ArrayList<>());
        trayectosRepository.save(trayecto);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/trayectos"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Trayecto[] trayectos = {trayecto};
        assertEquals(
            objectMapper.writeValueAsString(trayectos), 
            result.getResponse().getContentAsString());

    }

    @Test
    public void testIniciarTrayecto() throws Exception {

        // Arrange
        UbicacionDTO ubicacion = new UbicacionDTO();
        ubicacion.setLatitud(40.7128);
        ubicacion.setLongitud(-74.0060);

        // Act
        MvcResult result = mockMvc.perform(
                    MockMvcRequestBuilders.post("/api/trayectos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(ubicacion))
                    )
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Trayecto trayecto = trayectosRepository.findAll().get(0);
        assertEquals(toJson(trayecto.getId()), result.getResponse().getContentAsString());
    }

    @Test
    public void testRegistrarUbicacion() throws Exception {
        
        // Arrange
        Trayecto trayecto = new Trayecto();
        trayecto.setFechaHoraInicio(LocalDateTime.now());
        trayecto.setEnProceso(true);
        trayecto.setUbicaciones(new ArrayList<>());
        trayecto = trayectosRepository.save(trayecto);

        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setLatitud(40.7128);
        ubicacionDTO.setLongitud(-74.0060);
        String requestJson = toJson(ubicacionDTO);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/trayectos/"+trayecto.getId()+"/ubicaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Ubicacion ubicacion = ubicacionesRepository.findAll().get(0);
        assertEquals(toJson(ubicacion.getId()), result.getResponse().getContentAsString());

    }

    @Test
    public void testFinalizarTrayecto() throws Exception {
        // Arrange
        Trayecto trayecto = new Trayecto();
        trayecto.setFechaHoraInicio(LocalDateTime.now());
        trayecto.setEnProceso(true);
        trayecto.setUbicaciones(new ArrayList<>());
        trayecto = trayectosRepository.save(trayecto);

        UbicacionDTO ubicacion = new UbicacionDTO();
        ubicacion.setLatitud(40.7128);
        ubicacion.setLongitud(-74.0060);
        String requestJson = toJson(ubicacion);


        // Act
        MvcResult result = mockMvc.perform(
                    MockMvcRequestBuilders.post("/api/trayectos/"+trayecto.getId()+"/finalizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Trayecto trayectoFinalizado = trayectosRepository.findById(trayecto.getId()).get();

        assertEquals(
            toJson(trayectoFinalizado.getId()), 
            result.getResponse().getContentAsString());

        assertFalse(trayectoFinalizado.isEnProceso());

    }

    @Test
    public void testConsultarTrayecto() throws Exception {
        
        // Arrange
        Trayecto trayecto = new Trayecto();
        trayecto.setFechaHoraInicio(LocalDateTime.now());
        trayecto.setUbicaciones(new ArrayList<>());
        trayecto = trayectosRepository.save(trayecto);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/trayectos/"+trayecto.getId()))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String expected = toJson(trayecto);
        assertEquals(expected, result.getResponse().getContentAsString());
    }

}