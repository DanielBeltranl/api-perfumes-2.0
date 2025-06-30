package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Long> {


    List<Envio> findByOrigen(String origen);

    
}