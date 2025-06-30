package com.perfulandia.perfulandia.controller;
import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.service.EnvioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EnvioController.class)
class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnvioService envioService;

    private ObjectMapper objectMapper;
    private Envio envio1;
    private Envio envio2;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        
        envio1 = Envio.builder()
                .id(1)
                .nombre("Juan Pérez")
                .destino("Santiago")
                .estado("En tránsito")
                .rutaOptima("Ruta A")
                .origen("Valparaíso")
                .build();
        
        envio2 = Envio.builder()
                .id(2)
                .nombre("María García")
                .destino("Concepción")
                .estado("Pendiente")
                .rutaOptima("Ruta B")
                .origen("Santiago")
                .build();
    }

    @Test
    @WithMockUser
    void obtenerTodos_conEnvios_retornaOk() throws Exception {
        when(envioService.findAll()).thenReturn(Arrays.asList(envio1));

        String expectedJson = objectMapper.writeValueAsString(Arrays.asList(envio1));

        mockMvc.perform(get("/perfulandia/api/envios/listaEnvios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        System.out.println("obtenerTodos_conEnvios_retornaOk: " + expectedJson);
    }

    @Test
    @WithMockUser
    void obtenerTodos_sinEnvios_retornaNoContent() throws Exception {
        when(envioService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/perfulandia/api/envios/listaEnvios"))
                .andExpect(status().isNoContent());

        System.out.println("obtenerTodos_sinEnvios_retornaNoContent: []");
    }

    @Test
    @WithMockUser
    void obtenerEnviosValparaiso_conEnvios_retornaOk() throws Exception {
        when(envioService.findByOrigen("valparaiso")).thenReturn(Arrays.asList(envio1));

        String expectedJson = objectMapper.writeValueAsString(Arrays.asList(envio1));

        mockMvc.perform(get("/perfulandia/api/envios/listaenvios/valparaiso"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        System.out.println("obtenerEnviosValparaiso_conEnvios_retornaOk: " + expectedJson);
    }

    @Test
    @WithMockUser
    void obtenerEnviosValparaiso_sinEnvios_retornaNoContent() throws Exception {
        when(envioService.findByOrigen("valparaiso")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/perfulandia/api/envios/listaenvios/valparaiso"))
                .andExpect(status().isNoContent());

        System.out.println("obtenerEnviosValparaiso_sinEnvios_retornaNoContent: []");
    }

    @Test
    @WithMockUser
    void obtenerEnviosSantiago_conEnvios_retornaOk() throws Exception {
        when(envioService.findByOrigen("santiago")).thenReturn(Arrays.asList(envio2));

        String expectedJson = objectMapper.writeValueAsString(Arrays.asList(envio2));


        mockMvc.perform(get("/perfulandia/api/envios/listaenvios/Santiago"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        System.out.println("obtenerEnviosSantiago_conEnvios_retornaOk: " + expectedJson);
    }

    @Test
    @WithMockUser
    void obtenerEnviosSantiago_sinEnvios_retornaNoContent() throws Exception {
        when(envioService.findByOrigen("santiago")).thenReturn(Collections.emptyList());

        
        mockMvc.perform(get("/perfulandia/api/envios/listaenvios/Santiago"))
                .andExpect(status().isNoContent());

        System.out.println("obtenerEnviosSantiago_sinEnvios_retornaNoContent: []");
    }

    @Test
    @WithMockUser
    void obtenerEnviosConcepcion_conEnvios_retornaOk() throws Exception {
        when(envioService.findByOrigen("concepcion")).thenReturn(Arrays.asList(envio1));

        String expectedJson = objectMapper.writeValueAsString(Arrays.asList(envio1));

        mockMvc.perform(get("/perfulandia/api/envios/listaenvios/concepcion"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        System.out.println("obtenerEnviosConcepcion_conEnvios_retornaOk: " + expectedJson);
    }

    @Test
    @WithMockUser
    void obtenerEnviosConcepcion_sinEnvios_retornaNoContent() throws Exception {
        when(envioService.findByOrigen("concepcion")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/perfulandia/api/envios/listaenvios/concepcion"))
                .andExpect(status().isNoContent());

        System.out.println("obtenerEnviosConcepcion_sinEnvios_retornaNoContent: []");
    }

    @Test
    @WithMockUser
    void obtenerPorId_existente_retornaOk() throws Exception {
        when(envioService.findById(1L)).thenReturn(envio1);

        String expectedJson = objectMapper.writeValueAsString(envio1);

        mockMvc.perform(get("/perfulandia/api/envios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        System.out.println("obtenerPorId_existente_retornaOk: " + expectedJson);
    }

    @Test
    @WithMockUser
    void obtenerPorId_noExistente_retornaNotFound() throws Exception {
        when(envioService.findById(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/perfulandia/api/envios/1"))
                .andExpect(status().isNotFound());

        System.out.println("obtenerPorId_noExistente_retornaNotFound: null");
    }

    @Test
    @WithMockUser
    void guardar_envio_retornaCreated() throws Exception {
        when(envioService.save(any(Envio.class))).thenReturn(envio2);

        String requestJson = objectMapper.writeValueAsString(envio2);
        String expectedJson = objectMapper.writeValueAsString(envio2);

        mockMvc.perform(post("/perfulandia/api/envios/guardar")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        System.out.println("guardar_envio_retornaCreated: " + expectedJson);
    }

    @Test
    @WithMockUser
    void actualizarRutaOptima_existente_retornaOk() throws Exception {
        Envio envioRequest = Envio.builder()
                .rutaOptima("Ruta B Optimizada")
                .build();

        Envio envioActualizado = Envio.builder()
                .id(1)
                .nombre("Juan Pérez")
                .destino("Santiago")
                .estado("En tránsito")
                .rutaOptima("Ruta B Optimizada")
                .origen("Valparaíso")
                .build();

        when(envioService.findById(1L)).thenReturn(envio1);
        when(envioService.save(any(Envio.class))).thenReturn(envioActualizado);

        String requestJson = objectMapper.writeValueAsString(envioRequest);
        String expectedJson = objectMapper.writeValueAsString(envioActualizado);

        mockMvc.perform(put("/perfulandia/api/envios/1/rutaoptima")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        System.out.println("actualizarRutaOptima_existente_retornaOk: " + expectedJson);
    }

    @Test
    @WithMockUser
    void actualizarRutaOptima_noExistente_retornaNotFound() throws Exception {
        Envio envioRequest = Envio.builder()
                .rutaOptima("Ruta Nueva")
                .build();

        when(envioService.findById(1L)).thenThrow(new RuntimeException());

        String requestJson = objectMapper.writeValueAsString(envioRequest);

        mockMvc.perform(put("/perfulandia/api/envios/1/rutaoptima")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isNotFound());

        System.out.println("actualizarRutaOptima_noExistente_retornaNotFound: null");
    }

    @Test
    @WithMockUser
    void eliminar_existente_retornaNoContent() throws Exception {
        doNothing().when(envioService).delete(1L);

        mockMvc.perform(delete("/perfulandia/api/envios/1")
                .with(csrf()))
                .andExpect(status().isNoContent());

        System.out.println("eliminar_existente_retornaNoContent: null");
    }

    @Test
    @WithMockUser
    void eliminar_noExistente_retornaNotFound() throws Exception {
        doThrow(new RuntimeException()).when(envioService).delete(1L);

        mockMvc.perform(delete("/perfulandia/api/envios/1")
                .with(csrf()))
                .andExpect(status().isNotFound());

        System.out.println("eliminar_noExistente_retornaNotFound: null");
    }
}