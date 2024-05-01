package com.example.CRUD.models;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Persona {
    private long id;
    private String nombre;
    private int edad;
    private String trabajo;
    private String correo;
    private String direccion;
}
