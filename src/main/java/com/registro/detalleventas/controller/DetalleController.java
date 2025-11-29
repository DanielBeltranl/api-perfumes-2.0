package com.registro.detalleventas.controller;


import com.registro.detalleventas.model.DetalleVenta;
import com.registro.detalleventas.model.ResponseFormat;
import com.registro.detalleventas.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
public class DetalleController {

    @Autowired
    private DetalleService detalleService;


    @GetMapping("/get-detalle-venta/{id}")
    public ResponseEntity<ResponseFormat> getDetalleVenta(@PathVariable long id){
        try{
            List<DetalleVenta> lista = detalleService.getDetalle(id);
            if(lista.isEmpty()){
                ResponseFormat response = new ResponseFormat(204, "No hay detalle spara la venta", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            ResponseFormat response = new ResponseFormat(200, "Lista recuperada exitosamente", lista);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            ResponseFormat response = new ResponseFormat(500, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<ResponseFormat> guardar(@RequestBody List<DetalleVenta> venta){
        try{
            List<DetalleVenta> lista = detalleService.saveDetalle(venta);
            ResponseFormat response = new ResponseFormat(201, "Detalle guardado exitosamente", lista);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            ResponseFormat response = new ResponseFormat(500, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }


}
