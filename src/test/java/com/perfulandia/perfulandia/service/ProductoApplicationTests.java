package com.perfulandia.perfulandia.service;

import com.perfulandia.perfulandia.controller.ProductoController;
import com.perfulandia.perfulandia.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductoApplicationTests {

    @Autowired
    private ProductoController productoController;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void contextLoads() {
        
    }

    @Test
    void controllerLoads() {
        
        assertThat(productoController).isNotNull();
    }

    @Test
    void serviceLoads() {
        
        assertThat(productoService).isNotNull();
    }

    @Test
    void repositoryLoads() {
        
        assertThat(productoRepository).isNotNull();
    }
}
