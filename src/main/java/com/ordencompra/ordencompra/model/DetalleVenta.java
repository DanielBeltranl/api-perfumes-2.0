package com.ordencompra.ordencompra.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVenta {

    private Long idDetalle;
    private Long idVenta;
    private Long idProducto;
    private String nombreProducto;
    protected Double precioUnitario;
    private int cantidad;

}
