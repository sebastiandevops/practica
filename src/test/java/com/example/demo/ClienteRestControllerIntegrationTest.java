package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebastian.practica.controller.ClienteController;
import com.sebastian.practica.model.Cliente;

/**
 * ClienteRestControllerIntegrationTest class
 * @author Sebastián
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = ConfiguradorSpring.class)
public class ClienteRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteController ctr;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * MockMvc init
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    /**
     * Test for GET request (findById)
     * @throws Exception
     */
    @Test
    public void whenValidInputSearchById_thenReturns200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepe", "Pérez", "CC", 1053778162l,
                33, "Manizales");
        this.mvc.perform(get("http://localhost:9898/api/v1/1")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
        .andExpect(status().isOk());
    }

    /**
     * Test for GET request (findByIdNumber)
     * @throws Exception
     */
    @Test
    public void whenValidInputSearchByCC_thenReturns200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepe", "Pérez", "CC", 1053778162l,
                33, "Manizales");
        this.mvc.perform(get("http://localhost:9898/api/v1/cc/1053778162")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
        .andExpect(status().isOk());
    }

    /**
     * Test for GET request (findByAge)
     * @throws Exception
     */
    @Test
    public void whenValidInputSearchByAge_thenReturns200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepe", "Pérez", "CC", 1053778162l,
                33, "Manizales");
        this.mvc.perform(get("http://localhost:9898/api/v1/age/33")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
        .andExpect(status().isOk());
    }

    /**
     * Test for POST request (createClient)
     * @throws Exception
     */
    @Test
    public void whenValidInputPost_thenReturns200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepe", "Pérez", "CC", 1053778162l,
                33, "Manizales");
        this.mvc.perform(post("http://localhost:9898/api/v1/clients")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
        .andExpect(status().isOk());
    }

    /**
     * Test for DELETE request (delete by id)
     * @throws Exception
     */
    @Test
    public void whenValidInputDelete_thenReturns200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(2, "Pepe", "Sánchez", "CC", 1053778163l,
                33, "Manizales");
        this.mvc.perform(delete("http://localhost:9898/api/v1/clients/1053778163")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
        .andExpect(status().isOk());
    }

    /**
     * Test for GET request (getAllClientes).
     * @throws Exception
     */
    @Test
    public void whenValidInputSearchAll_thenReturns200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepe", "Pérez", "CC", 1053778162l,
                33, "Manizales");
        this.mvc.perform(get("http://localhost:9898/api/v1/clients")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
        .andExpect(status().isOk());
    }
}
