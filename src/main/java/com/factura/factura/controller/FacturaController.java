package com.factura.factura.controller;


import com.factura.factura.model.DetalleVenta;
import com.factura.factura.model.Factura;
import com.factura.factura.model.ResponseFormat;
import com.factura.factura.services.FacturaService;
import com.factura.factura.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private PdfService pdfService;

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

            List<DetalleVenta> detalle = facturaService.getDetalleVenta(factura.getIdVenta());

            factura.setDetalleVenta(detalle);
            factura.setEstadoDespacho("Pendiente");

            if(detalle == null || detalle.isEmpty()){
                ResponseFormat responseFormat = new ResponseFormat(204, "No hay detalle para la factura", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseFormat);
            }
            ResponseFormat responseFormat = new ResponseFormat(200, "Factura encontrada", factura);
            return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
        } catch (Exception e) {
            ResponseFormat responseFormat = new ResponseFormat(500, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseFormat);
        }
    }


    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> generarPdfFactura(@PathVariable Long id) {
        try {
            Factura factura = facturaService.findById(id);
            if (factura == null) {
                ResponseFormat responseFormat = new ResponseFormat(404, "Factura no encontrada", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseFormat);
            }

            // Obtener detalles desde tu service
            List<DetalleVenta> detalles = facturaService.getDetalleVenta(factura.getIdVenta());
            factura.setDetalleVenta(detalles);

            if (detalles == null || detalles.isEmpty()) {
                ResponseFormat responseFormat = new ResponseFormat(204, "No hay detalle para la factura", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseFormat);
            }


            double totalBruto = detalles.stream()
                    .mapToDouble(d -> (d.getPrecioUnitario() != null ? d.getPrecioUnitario() : 0.0) * d.getCantidad())
                    .sum();

            double ivaRate = 0.19; // 19% IVA, cambia si necesitas otro porcentaje
            double iva = Math.round(totalBruto * ivaRate * 100.0) / 100.0;
            double totalConIva = Math.round((totalBruto + iva) * 100.0) / 100.0;


            byte[] pdfBytes = pdfService.generarFacturaPdf(factura, totalBruto, iva, totalConIva);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("factura-" + id + ".pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            ResponseFormat responseFormat = new ResponseFormat(500, "Error generando PDF: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseFormat);
        }
    }

}
