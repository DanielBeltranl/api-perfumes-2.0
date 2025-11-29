package com.factura.factura.services;

import com.factura.factura.model.Factura;
import com.factura.factura.repository.FacturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

}
