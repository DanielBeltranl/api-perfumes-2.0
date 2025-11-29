package com.factura.factura.repository;

import com.factura.factura.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
