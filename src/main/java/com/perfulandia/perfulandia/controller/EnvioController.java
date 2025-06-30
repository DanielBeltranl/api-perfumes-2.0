package com.perfulandia.perfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/perfulandia/api/envios")
@RequiredArgsConstructor
@Tag(name="Envios", description= "Operaciones relacionadas con los envios ")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping("/listaEnvios")
    @Operation(summary = "Obtener todos los envios ", description = "obtener todos los envios de todas las sucursales")
    public ResponseEntity<List<Envio>> obtenerTodos() {
        List<Envio> envios = envioService.findAll();
        if ( envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/listaenvios/valparaiso")
        @Operation(summary = "Obtener todos los envios de Valparaiso ", description = "obtener todos los envios de la sucursal de valparaiso")
    public ResponseEntity<List<Envio>> obtenerEnviosValparaiso() {
        List<Envio> envios = envioService.findByOrigen("valparaiso");
        if ( envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/listaenvios/Santiago")
        @Operation(summary = "Obtener todos los envios de Santiago ", description = "obtener todos los envios de la sucursal de Santiago")
    public ResponseEntity<List<Envio>> obtenerEnviosSantiago() {
        List<Envio> envios = envioService.findByOrigen("santiago");
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/listaenvios/concepcion")
        @Operation(summary = "Obtener todos los envios de Concepcion ", description = "obtener todos los envios de la sucursal de concepcion")
    public ResponseEntity<List<Envio>> obtenerEnviosConcepcion() {
        List<Envio> envios = envioService.findByOrigen("concepcion");
        if ( envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/{id}")
     @Operation(summary = "Obtener un envio por su ID ", description = "obtener solo un envio basandose en su Id")
    public ResponseEntity<Envio> obtenerPorId(@PathVariable Long id) {
        try {
            Envio envio = envioService.findById(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
     @Operation(summary = "Guardar un envio ", description = "Guardar un solo envio ")
    public ResponseEntity<Envio> guardar(@RequestBody Envio envio) {
        Envio nuevoEnvio = envioService.save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
    }

    @PutMapping("/{id}/rutaoptima")
        @Operation(summary = "Actualizar ruta optima", description = "Actualizar solo el apartado de ruta optima para que el repartidor tenga la mejor ruta hacia su destino")
    public ResponseEntity<Envio> actualizarRutaOptima(@PathVariable Long id, @RequestBody Envio envioRequest) {
        try {
            Envio envioExistente = envioService.findById(id);
            envioExistente.setRutaOptima(envioRequest.getRutaOptima());
            envioService.save(envioExistente);
            return ResponseEntity.ok(envioExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar envio por ID", description = "Eliminar un envio solo por su ID")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            envioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}