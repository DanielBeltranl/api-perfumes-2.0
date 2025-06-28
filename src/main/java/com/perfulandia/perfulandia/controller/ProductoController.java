package com.perfulandia.perfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.perfulandia.perfulandia.model.Producto;
import com.perfulandia.perfulandia.service.ProductoService;





@RestController
@RequestMapping("/perfulandia/api/producto")
@Tag(name = "ProductoController", description = "Controlador para manejar productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos disponibles")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(productos);
        }
        
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto específico por su ID")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") Long id) {
        Producto producto = productoService.getProductoById(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }


    @PostMapping("/guardar")
    @Operation(summary = "Guardar producto", description = "Guarda un nuevo producto o actualiza uno existente")
    public Producto guardarProducto(@Valid @RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por su ID")
    public ResponseEntity<String> EliminarProducto(@PathVariable("id") Long id) {
        productoService.deleteProducto(id);
        if (productoService.getProductoById(id) != null) {
            return ResponseEntity.status(500).body("Error al eliminar el producto");
        }
        else{
            return ResponseEntity.status(200).body("Producto eliminado con éxito");
        }
    }
}

