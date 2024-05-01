package com.example.CRUD.controllers;

import com.example.CRUD.models.Persona;
import com.example.CRUD.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaController(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    @GetMapping
    public List<Persona> listarPersonas(){
        return personaRepository.listarPersonas();
    }

    @GetMapping("/{id}")
    public Persona obtenerPersona(@PathVariable int id){
        return personaRepository.getPersonaPorId(id);
    }

    @PostMapping
    public ResponseEntity<String> addPersona (@RequestBody Persona persona){
        if(personaRepository.emailNoExiste(persona)){
            personaRepository.addPersona(persona);
            int idNuevo = (int)persona.getId();
            return ResponseEntity.status(HttpStatus.CREATED).body("Persona agregada correctamente con ID: "+idNuevo+" !");
        }else {
            return ResponseEntity.ok("El correo está repetido! No se insertó ningún registro");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyPersona(@PathVariable int id, @RequestBody Persona persona){
        personaRepository.editPersona(id,persona);
        return ResponseEntity.ok("Persona con ID: "+ id +" modificada correctamente!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePersona(@PathVariable int id) {
        personaRepository.removePersona(id);
        return ResponseEntity.ok("Persona con ID: "+ id +" eliminada correctamente!");
    }
}
