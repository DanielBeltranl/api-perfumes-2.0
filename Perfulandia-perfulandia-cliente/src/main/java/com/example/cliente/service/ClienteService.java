package com.example.cliente.service;


import com.example.cliente.model.Cliente;
import com.example.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> findAll (){


        return clienteRepository.findAll();

    }

    public Cliente findById (long id){

        return clienteRepository.findById(id).get();

    }

    public Cliente save (Cliente cliente){

        return clienteRepository.save(cliente);

    }

    public void deleteById (Long id){

        clienteRepository.deleteById(id);

    }

}
