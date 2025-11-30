package com.factura.factura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "factura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false )
    private Long idFactura;

    @Column (name = "id_venta", nullable = false)
    private Long idVenta;

    @Column (name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "apellido_cliente", nullable = false)
    private String apellidoCliente;

    @Column(name = "run_cliente", nullable = false)
    private String runCliente;

    @Column(name = "fecha_emision", nullable = false)
    private String fechaEmision;

    @Column(name = "total_pago", nullable = false)
    private Double totalPago;

    @Column(name = "estado_despacho", nullable = false)
    private String estadoDespacho;

    @Transient
    private List<DetalleVenta> detalleVenta;

}
