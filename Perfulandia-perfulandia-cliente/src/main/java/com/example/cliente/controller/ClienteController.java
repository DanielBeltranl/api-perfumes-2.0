package com.example.cliente.controller;

import com.example.cliente.model.Cliente;
import com.example.cliente.repository.ClienteRepository;
import com.example.cliente.service.ClienteService;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfulandia/api/clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){

    List<Cliente> clientes = clienteService.findAll();

    if (clientes.isEmpty()){

        return ResponseEntity.noContent().build();

    }

    return ResponseEntity.ok(clientes);

    }

    @PostMapping
    private ResponseEntity<Cliente> guardar (@RequestBody Cliente cliente){

        Cliente ClienteNuevo = clienteService.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteNuevo);

    }

    @GetMapping("/{id}")
    private ResponseEntity<Cliente> buscarCliente (@PathVariable Long id){

        try {

            Cliente cli = clienteService.findById(id);


            return ResponseEntity.ok(cli);


        }catch (Exception e){

            return ResponseEntity.notFound().build();

        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Cliente> actualizarCliente (@PathVariable Long id, @RequestBody Cliente cliente){

        try {

            Cliente cli = clienteService.findById(id);

            cli.setId_cliente(id);
            cli.setRun_cliente(cliente.getRun_cliente());
            cli.setNombre_cliente(cliente.getNombre_cliente());
            cli.setApellido_cliente(cliente.getApellido_cliente());
            cli.setDireccion_cliente(cliente.getDireccion_cliente());

            clienteService.save(cli);

            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        }catch (Exception e){

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
private ResponseEntity<Cliente> eliminarCliente (@PathVariable Long id){

        try {

            clienteService.deleteById(id);

            return ResponseEntity.noContent().build();

        }catch (Exception e){

            return ResponseEntity.notFound().build();
        }


}

}
