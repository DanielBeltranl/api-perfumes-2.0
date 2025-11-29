package com.ordencompra.ordencompra.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orden_compra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta", nullable = false)
    private Long idVenta;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "apellido_cliente", nullable = false)
    private String apellidoCliente;

    @Column(name = "run_cliente", nullable = false)
    private String runCliente;

    @Column(name = "direccion_envio", nullable = false)
    private String direccionEnvio;

    @Column (name = "comuna", nullable = false)
    private String comuna;

    @Column(name = "telefono_contacto", nullable = false)
    private int telefonoContacto;

    @Column(name = "total_compra", nullable = false)
    private Double totalCompra;

    @Transient
    private List<DetalleVenta> detalleVenta;


}
