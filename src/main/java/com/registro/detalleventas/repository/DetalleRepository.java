package com.registro.detalleventas.repository;

import com.registro.detalleventas.model.DetalleVenta;
import com.registro.detalleventas.service.DetalleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DetalleRepository extends JpaRepository<DetalleVenta, Long >{

    List<DetalleVenta> findByIdVenta(Long idVenta);
}
