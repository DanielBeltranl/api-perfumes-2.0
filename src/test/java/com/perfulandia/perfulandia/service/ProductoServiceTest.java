package com.perfulandia.perfulandia.service;

import com.perfulandia.perfulandia.model.Producto;
import com.perfulandia.perfulandia.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        producto1 = Producto.builder()
                .id(1)
                .nombre("Producto 1")
                .descripcion("Descripcion 1")
                .stock(10)
                .precio(new BigDecimal("100.0"))
                .build();

        producto2 = Producto.builder()
                .id(2)
                .nombre("Producto 2")
                .descripcion("Descripcion 2")
                .stock(20)
                .precio(new BigDecimal("200.0"))
                .build();
    }

    @Test
    public void getAllProductos() {
        List<Producto> productos = Arrays.asList(producto1, producto2);
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> result = productoService.getAllProductos();

        assertEquals(2, result.size());
        assertEquals("Producto 1", result.get(0).getNombre());
    }

    @Test
    public void getProductoById() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto1));

        Producto result = productoService.getProductoById(1L);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Producto 1", result.getNombre());
    }

    @Test
    public void getProductoById_NotFound() {
        when(productoRepository.findById(999L)).thenReturn(Optional.empty());

        Producto result = productoService.getProductoById(999L);

        assertNull(result);
    }

    @Test
    public void saveProducto() {
        Producto nuevoProducto = Producto.builder()
                .nombre("Producto Nuevo")
                .descripcion("Descripcion Nueva")
                .stock(5)
                .precio(new BigDecimal("50.0"))
                .build();

        Producto productoGuardado = Producto.builder()
                .id(3)
                .nombre("Producto Nuevo")
                .descripcion("Descripcion Nueva")
                .stock(5)
                .precio(new BigDecimal("50.0"))
                .build();

        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        Producto result = productoService.saveProducto(nuevoProducto);

        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals("Producto Nuevo", result.getNombre());
    }

    @Test
    public void deleteProducto() {
        doNothing().when(productoRepository).deleteById(1L);

        productoService.deleteProducto(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }
}
