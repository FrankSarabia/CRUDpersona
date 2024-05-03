package com.example.CRUD.controllers;

import com.example.CRUD.models.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class PersonaControllerTest2 {

    MockMvc mockMvc;

    // Contexto de aplicación web para la configuración del MockMvc

    @Autowired
    private WebApplicationContext webApplicationContext;

    // Configuración antes de cada prueba
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void listarPersonas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/personas/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void addPersona() throws Exception {
        Persona persona = new Persona();
        persona.setNombre("Francisco");
        persona.setEdad(23);
        persona.setTrabajo("Analyst");
        persona.setCorreo("francisco@gmail.com");
        persona.setDireccion("Calle1, #2");

        ObjectMapper mapper = new ObjectMapper();
        String personaJSON = mapper.writeValueAsString(persona);
        mockMvc.perform(MockMvcRequestBuilders.post("/personas/agregar").content(personaJSON)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}