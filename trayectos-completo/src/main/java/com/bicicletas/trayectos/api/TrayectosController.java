package com.bicicletas.trayectos.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bicicletas.trayectos.dataAccess.TrayectosRepository;
import com.bicicletas.trayectos.logica.TrayectosService;
import com.bicicletas.trayectos.modelo.Trayecto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/trayectos")
public class TrayectosController {

    @Autowired
    private TrayectosService trayectosService;

    @Autowired
    private TrayectosRepository trayectosRepository;

    // GET /api/trayectos
    @GetMapping
    public List<Trayecto> getTodosLosTrayectos() {
        return trayectosRepository.findAll();
    }

    // GET /api/trayectos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Trayecto> getTrayectoPorId(@PathVariable UUID id) {
        return trayectosRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/trayectos
    @PostMapping
    @Transactional
    public ResponseEntity<UUID> crearTrayecto(
        @RequestBody UbicacionDTO ubicacion
    ) {
        try {
            return ResponseEntity.ok(
                trayectosService.iniciarTrayecto(
                    ubicacion.getLongitud(),
                    ubicacion.getLatitud())
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // POST /api/trayectos/{id}/ubicaciones
    @PostMapping("/{id}/ubicaciones")
    @Transactional
    public ResponseEntity<UUID> agregarUbicacionATrayecto(
        @PathVariable UUID id,
        @RequestBody UbicacionDTO ubicacion) 
    {

        System.out.println("> id " + id);
        System.out.println("> long " + ubicacion.getLongitud());
        System.out.println("> lat  " + ubicacion.getLatitud());

        try {

            return ResponseEntity.ok(
                trayectosService.agregarUbicacionAlTrayecto(
                    id,
                    ubicacion.getLongitud(),
                    ubicacion.getLatitud())
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    // POST /api/trayectos/{id}/ubicaciones
    @Transactional
    @PostMapping("/{id}/finalizar")
    public ResponseEntity<UUID> finalizarTrayecto(
        @PathVariable UUID id,
        @RequestBody UbicacionDTO ubicacion) 
    {
        try {

            return ResponseEntity.ok(
                trayectosService.finalizarTrayecto(
                    id,
                    ubicacion.getLongitud(),
                    ubicacion.getLatitud())
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

@Data
@NoArgsConstructor
class UbicacionDTO {
    Double longitud;

    Double latitud;

}