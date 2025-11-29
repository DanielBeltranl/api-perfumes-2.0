package com.registro.detalleventas.service;


import com.registro.detalleventas.model.DetalleVenta;
import com.registro.detalleventas.repository.DetalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleService {

    @Autowired
    private DetalleRepository detalleRepository;

    public List<DetalleVenta> getDetalle(Long idVenta){
        return detalleRepository.findByIdVenta(idVenta);
    }

    public List<DetalleVenta> saveDetalle(List<DetalleVenta> detalle){
        return detalleRepository.saveAll(detalle);
    }


}
