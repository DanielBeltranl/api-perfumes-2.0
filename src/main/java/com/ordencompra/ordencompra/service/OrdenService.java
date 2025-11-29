package com.ordencompra.ordencompra.service;

import com.ordencompra.ordencompra.model.DetalleVenta;
import com.ordencompra.ordencompra.model.OrdenCompra;
import com.ordencompra.ordencompra.model.ResponseFormat;
import com.ordencompra.ordencompra.repository.OrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Transactional

public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private RestTemplate restTemplate;

     private final String DETALLE_VENTA_SERVICE_URL = "http://localhost:8081/detalle-venta/";

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

    public ResponseFormat guardarDetalle(List<DetalleVenta> detalleVenta){
        try{
            return restTemplate.postForObject("https://perfumes-2-0-n3sk.onrender.com/detalle/guardar", detalleVenta, ResponseFormat.class);
        } catch (Exception e) {

            return new ResponseFormat(500, "Error al guardar el detalle de venta: " + e.getMessage(), null);

        }
    }




}
