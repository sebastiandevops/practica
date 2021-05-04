package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.sebastian.practica.testing.Servicio;


@SpringBootTest
@ContextConfiguration(classes = ConfiguradorSpring.class)
class DemoWebApplicationTests {
    @Autowired
    Servicio miServicio;


    @Test
    public void countTest() {
        assertEquals(2, miServicio.searchAll().size());
    }

    @Test
    public void getIdTest() {
        assertEquals(1l, miServicio.searchAll().get(0).getId());
    }

    @Test
    public void getAgeTest() {
        assertEquals(33, miServicio.searchAll().get(0).getAge());
    }

    @Test
    public void getFirstName() {
        assertEquals("Pepe", miServicio.searchAll().get(0).getFirstName());
    }
}
