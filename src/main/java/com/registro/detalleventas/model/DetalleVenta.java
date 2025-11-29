package com.registro.detalleventas.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle", nullable = false )
    private Long idDetalle;

    @Column(name = "id_venta", nullable = false)
    private Long idVenta;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "precio_unitario", nullable = false)
    protected Double precioUnitario;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

}
