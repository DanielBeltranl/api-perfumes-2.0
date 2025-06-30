package com.perfulandia.perfulandia.service;

import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.Repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository EnvioRepository;

    public List<Envio> findAll() {
        return EnvioRepository.findAll();
    }

    public Envio findById(Long id) {
        return EnvioRepository.findById(id).get();
    }

    public Envio save(Envio envio) {
        return EnvioRepository.save(envio);
    }
    
    public void delete(Long id) {
        EnvioRepository.deleteById(id);

    }

    public List<Envio> findByOrigen(String origen) {
        return EnvioRepository.findByOrigen(origen);
    }

}