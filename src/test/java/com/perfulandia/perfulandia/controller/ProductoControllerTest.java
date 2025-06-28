package com.perfulandia.perfulandia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.perfulandia.model.Producto;
import com.perfulandia.perfulandia.service.ProductoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductoController.class, excludeAutoConfiguration = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        producto1 = Producto.builder()
                .id(1)
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .stock(10)
                .precio(new BigDecimal("100.00"))
                .build();

        producto2 = Producto.builder()
                .id(2)
                .nombre("Producto 2")
                .descripcion("Descripcion 2")
                .stock(20)
                .precio(new BigDecimal("200.00"))
                .build();
    }

    @Test
    public void getAllProductos() throws Exception {
        List<Producto> productos = Arrays.asList(producto1, producto2);
        when(productoService.getAllProductos()).thenReturn(productos);

        mockMvc.perform(get("/perfulandia/api/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getProductos_Empty() throws Exception {
        when(productoService.getAllProductos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/perfulandia/api/producto"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getProductoById() throws Exception {
        when(productoService.getProductoById(1L)).thenReturn(producto1);

        mockMvc.perform(get("/perfulandia/api/producto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Producto 1"));
    }

    @Test
    public void getProducto_NotFound() throws Exception {
        when(productoService.getProductoById(999L)).thenReturn(null);

        mockMvc.perform(get("/perfulandia/api/producto/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void guardarProducto() throws Exception {
        Producto nuevoProducto = Producto.builder()
                .nombre("Producto Nuevo")
                .descripcion("Descripcion Nueva")
                .stock(5)
                .precio(new BigDecimal("50.00"))
                .build();

        Producto productoConId = Producto.builder()
                .id(3)
                .nombre("Producto Nuevo")
                .descripcion("Descripcion Nueva")
                .stock(5)
                .precio(new BigDecimal("50.00"))
                .build();

        when(productoService.saveProducto(any(Producto.class))).thenReturn(productoConId);

        mockMvc.perform(post("/perfulandia/api/producto/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoProducto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Producto Nuevo"));
    }

    @Test
    public void eliminarProducto() throws Exception {
        doNothing().when(productoService).deleteProducto(1L);
        when(productoService.getProductoById(1L)).thenReturn(null);

        mockMvc.perform(delete("/perfulandia/api/producto/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado con éxito"));
    }
}
