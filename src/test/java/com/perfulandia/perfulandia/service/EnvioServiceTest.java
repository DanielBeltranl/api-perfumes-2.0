package com.perfulandia.perfulandia.service;

import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.Repository.EnvioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.Optional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_retornaListaDeEnvios() {
        Envio envio = new Envio();
        when(envioRepository.findAll()).thenReturn(Arrays.asList(envio));

        List<Envio> result = envioService.findAll();

        assertFalse(result.isEmpty());
        verify(envioRepository, times(1)).findAll();
    }

    @Test
    void findById_existente_retornaEnvio() {
        Envio envio = new Envio();
        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envio result = envioService.findById(1L);

        assertNotNull(result);
        verify(envioRepository, times(1)).findById(1L);
    }

    @Test
    void findById_noExistente_lanzaExcepcion() {
        when(envioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> envioService.findById(1L));
    }

    @Test
    void save_envio_retornaEnvioGuardado() {
        Envio envio = new Envio();
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio result = envioService.save(envio);

        assertEquals(envio, result);
        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    void delete_envio_existente_noLanzaExcepcion() {
        doNothing().when(envioRepository).deleteById(1L);

        assertDoesNotThrow(() -> envioService.delete(1L));
        verify(envioRepository, times(1)).deleteById(1L);
    }
}