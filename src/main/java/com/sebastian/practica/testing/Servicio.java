package com.sebastian.practica.testing;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sebastian.practica.model.Cliente;

@Service
public class Servicio {

    public List<Cliente> searchAll() {

        Cliente c1 = new Cliente(1l, "Pepe", "Pérez", "CC", 1053778162l,
                33, "Manizales");
        Cliente c2 = new Cliente(2l, "Mariana", "Arboleda", "CC", 1017218679,
                27, "Medellín");

        List<Cliente> lista = Arrays.asList(c1, c2);
        return lista;
    }

}
