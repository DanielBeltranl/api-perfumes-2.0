package com.ordencompra.ordencompra.controller;


import com.ordencompra.ordencompra.model.DetalleVenta;
import com.ordencompra.ordencompra.model.OrdenCompra;
import com.ordencompra.ordencompra.model.ResponseFormat;
import com.ordencompra.ordencompra.service.OrdenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden-compra")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping("/guardar")
    public ResponseEntity<ResponseFormat> guardarOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try{
            if(ordenCompra == null){
                ResponseFormat response = new ResponseFormat(400, "Orden de compra nula", null);
                return ResponseEntity.status(400).body(response);
            }
            OrdenCompra orden = ordenService.guardarOrden(ordenCompra);


            List<DetalleVenta> lista = orden.getDetalleVenta();
            lista.forEach(detalleVenta -> {detalleVenta.setIdVenta(orden.getIdVenta());});
            System.out.println(lista);
            ResponseFormat responseDetalle = ordenService.guardarDetalle(lista);

            if(responseDetalle.getStatusCode()!=201){
                ResponseFormat response = new ResponseFormat(500, responseDetalle.getStatusMessage(), null);
                return ResponseEntity.status(500).body(response);
            }

            ResponseFormat response = new ResponseFormat(200, "Orden de compra guardado", orden);
           return ResponseEntity.status(200).body(response);
        } catch (Exception e) {

            ResponseFormat response = new ResponseFormat(500,"Hubo un error al guardar la orden de compra" + e.getMessage(), null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseFormat> listarOrdenes(){

        try{
            List<OrdenCompra> detalle = ordenService.listarOrdenes();
            ResponseFormat response = new ResponseFormat(200, "Orden de compra listado", detalle);
            return ResponseEntity.status(200).body(response);

        } catch (Exception e) {

            ResponseFormat response = new ResponseFormat(500, "Hubo un error al listar las ordenes de compra" + e.getMessage(), null);
            return ResponseEntity.status(500).body(response);

        }

    }

    @GetMapping("/obtener/{idOrden}")
    public ResponseEntity<ResponseFormat> obtenerOrdenCompra(@PathVariable Long idOrden){
        try{
            if(idOrden == null){
                ResponseFormat response = new ResponseFormat(400, "Orden de compra nula", null);
                return ResponseEntity.status(400).body(response);
            }

            OrdenCompra orden = ordenService.obtenerOrdenPorId(idOrden);
            ResponseFormat response = new ResponseFormat(200, "Orden de compra encontrado", orden);
            return ResponseEntity.status(200).body(response);

        } catch (Exception e) {
            ResponseFormat response = new ResponseFormat(500,"Hubo un error al buscar la orden de compra: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/eliminar/{idOrden}")
    public ResponseEntity<ResponseFormat> eliminarOrdenCompra(@PathVariable Long idOrden){
        try{
            if(idOrden == null){
                ResponseFormat response = new ResponseFormat(400, "Orden de compra nula", null);
                return ResponseEntity.status(400).body(response);
            }
            ordenService.eliminarOrdenPorId(idOrden);
            ResponseFormat response = new ResponseFormat(200, "Orden de compra eliminado", null);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            ResponseFormat response = new ResponseFormat(500, "Hubo un error al eliminar la orden de compra: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(response);
        }
    }

}
