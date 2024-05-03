package com.example.CRUD.controllers;

import com.example.CRUD.models.Persona;
import com.example.CRUD.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PersonaControllerTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaController personaController;

    private Persona persona;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        persona = new Persona();
        persona.setNombre("Francisco");
        persona.setEdad(23);
        persona.setTrabajo("Analyst");
        persona.setCorreo("francisco@gmail.com");
        persona.setDireccion("Calle1, #2");
    }
    @Test
    void listarPersonas() {
        when(personaRepository.listarPersonas()).thenReturn(Arrays.asList(persona));
        when(personaRepository.listarPersonas()).thenReturn(Arrays.asList(
                new Persona(1L,"Juan",20,"Analista","juan@gmail.com","Calle 1, #2, Colonia 1"),
                new Persona(2L,"Jose",21,"Analista","jose@gmail.com","Calle 2, #3, Colonia 2")
        ));

        List<Persona> personas = personaController.listarPersonas();
        assertEquals(2,personas.size());
    }

    @Test
    void obtenerPersona() {
        when(personaRepository.getPersonaPorId(1L)).thenReturn(new Persona(1L,"Juan",20,"Analista","juan@gmail.com","Calle 1, #2, Colonia 1"));
        Persona personaTest = personaController.obtenerPersona(1);
        assertNotNull(personaTest);
    }

    @Test
    void addPersona() {
        personaController.addPersona(persona);
        verify(personaRepository).addPersona(persona);
    }

    @Test
    void modifyPersona() {
        when(personaRepository.getPersonaPorId(1L)).thenReturn(persona);
        persona.setNombre("Josesito");
        personaController.modifyPersona(1,persona);
        verify(personaRepository).editPersona(1,persona);
        assertEquals(persona.getNombre(),"Josesito");
    }

    @Test
    void removePersona() {
        personaController.removePersona(1);
        verify(personaRepository).removePersona(1);
    }
}