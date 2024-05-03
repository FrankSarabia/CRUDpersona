package com.example.CRUD.testingweb;

import com.example.CRUD.controllers.PersonaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class testingWeb {
    @Autowired
    private PersonaController controller;

    @Test
    void contextLoads(){
        assertThat(controller).isNotNull();
    }
}
