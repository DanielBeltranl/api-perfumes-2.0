package com.factura.factura.services;

import com.factura.factura.model.DetalleVenta;
import com.factura.factura.model.Factura;
import com.factura.factura.model.ResponseFormat;
import com.factura.factura.repository.FacturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public Factura save(Factura factura) {

        return facturaRepository.save(factura);
    }

    public Factura findById(Long id) {

        return facturaRepository.findById(id).orElse(null);
    }

    public List<Factura> findAll() {

        return facturaRepository.findAll();
    }

    public List<DetalleVenta> getDetalleVenta(Long id) {
        String url = "https://perfumes-2-0-n3sk.onrender.com/detalle/get-detalle-venta/" + id;

        ResponseEntity<ResponseFormat> resp = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                ResponseFormat.class
        );

        ResponseFormat wrapper = resp.getBody();
        if (wrapper == null || wrapper.getData() == null) {
            return Collections.emptyList();
        }


        List<DetalleVenta> detalles = mapper.convertValue(wrapper.getData(), new TypeReference<List<DetalleVenta>>() {});
        return detalles;
    }



}
