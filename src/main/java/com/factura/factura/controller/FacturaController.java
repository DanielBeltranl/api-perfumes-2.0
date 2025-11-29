package com.factura.factura.controller;


import com.factura.factura.model.Factura;
import com.factura.factura.model.ResponseFormat;
import com.factura.factura.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/guardar")
    public ResponseEntity<ResponseFormat> guardarFactura(@RequestBody  Factura factura) {
        try{
            if(factura ==null){
                ResponseFormat responseFormat = new ResponseFormat(400, "Factura nula", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseFormat);
            }
            Factura newFactura = facturaService.save(factura);
            ResponseFormat responseFormat = new ResponseFormat(200, "Factura guardada exitosamente", newFactura);
            return ResponseEntity.status(HttpStatus.OK).body(responseFormat);

        } catch (Exception e) {
            ResponseFormat responseFormat = new ResponseFormat(500, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseFormat);

        }
    }

    @GetMapping("/get-factura/{id}")
    public ResponseEntity<ResponseFormat> getFactura(@PathVariable Long id) {
        try{
            Factura factura = facturaService.findById(id);
            if(factura == null){
                ResponseFormat responseFormat = new ResponseFormat(400, "Factura nula", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseFormat);
            }
            ResponseFormat responseFormat = new ResponseFormat(200, "Factura encontrada", factura);
            return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
        } catch (Exception e) {
            ResponseFormat responseFormat = new ResponseFormat(500, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseFormat);
        }
    }

}
