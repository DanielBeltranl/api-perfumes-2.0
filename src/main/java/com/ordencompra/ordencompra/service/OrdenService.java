package com.ordencompra.ordencompra.service;

import com.ordencompra.ordencompra.model.OrdenCompra;
import com.ordencompra.ordencompra.repository.OrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional

public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public OrdenCompra guardarOrden(OrdenCompra ordenCompra){
        return ordenRepository.save(ordenCompra);
    }

    public List<OrdenCompra> listarOrdenes(){
        return ordenRepository.findAll();
    }

    public OrdenCompra obtenerOrdenPorId(Long id){
        return ordenRepository.findById(id).orElse(null);
    }

    public void eliminarOrdenPorId(Long id){
        ordenRepository.deleteById(id);
    }




}
