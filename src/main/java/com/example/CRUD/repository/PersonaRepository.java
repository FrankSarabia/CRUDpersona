package com.example.CRUD.repository;

import com.example.CRUD.models.Persona;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonaRepository {
    private List<Persona> Personas = new ArrayList<>();
    private long SiguienteId = 1;

    public List<Persona> listarPersonas(){
        return this.Personas;
    }

    public Persona getPersonaPorId (long id) {
        for (Persona individuo : Personas){
            if (individuo.getId() == id){
                return individuo;
            }
        }
        return null;
    }

    public void addPersona (Persona nuevaPersona){
        nuevaPersona.setId(SiguienteId++);
        Personas.add(nuevaPersona);
    }

    public void editPersona(long id, Persona personaNueva){
        Persona individuo = getPersonaPorId(id);
        if(individuo != null){
            int j = Personas.indexOf(individuo);
            personaNueva.setId(id);
            Personas.set( j, personaNueva);
        }
    }

    public boolean emailNoExiste(Persona persona){
        for (Persona individuo : Personas){
            if (Objects.equals(individuo.getCorreo(), persona.getCorreo())){
                return false;
            }
        }
        return true;
    }

    public void removePersona(int id){
        Persona individuo = getPersonaPorId(id);
        if(individuo != null){
            int j = Personas.indexOf(individuo);
            Personas.remove(j);
        }
    }
}
