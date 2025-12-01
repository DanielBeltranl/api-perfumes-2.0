package com.ordencompra.ordencompra.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class FacturaDTO {

    private Long idFactura;

    private Long idVenta;

    private String nombreCliente;

    private String apellidoCliente;

    private String runCliente;

    private String fechaEmision;

    private Double totalPago;

    private String estadoDespacho;


}
