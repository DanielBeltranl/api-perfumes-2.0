package com.perfulandia.perfulandia.repository;

import com.perfulandia.perfulandia.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar productos por nombre o precio
    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByPrecioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);

}
