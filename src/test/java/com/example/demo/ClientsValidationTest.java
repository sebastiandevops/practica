package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
 * Data validation test
 * @author Sebastián
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = ConfiguradorSpring.class)
public class ClientsValidationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteController ctr;

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
     * Invalid firstName test
     * @throws Exception
     */
    @Test
    public void whenPutRequestHasInvalidFirstName_thenResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "P", "Pérez", "CC", 1053778162l,
                33, "Manizales");
        System.out.println(this.mvc.perform(put("http://localhost:9898/api/v1/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest()));
    }

    /**
     * Invalid lastName test
     * @throws Exception
     */
    @Test
    public void whenPutRequestHasInvalidLastName_thenResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepito", "P", "CC", 1053778162l,
                33, "Manizales");
        System.out.println(this.mvc.perform(put("http://localhost:9898/api/v1/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest()));
    }

    /**
     * Invalid age test
     * @throws Exception
     */
    @Test
    public void whenPutRequestHasInvalidAge_thenResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepito", "Pérez", "CC", 1053778162l,
                -1, "Manizales");
        System.out.println(this.mvc.perform(put("http://localhost:9898/api/v1/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest()));
    }

    /**
     * Invalid idNumber test
     * @throws Exception
     */
    @Test
    public void whenPutRequestHasInvalidIdNumber_thenResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, "Pepito", "Pérez", "CC", 1053,
                33, "Manizales");
        System.out.println(this.mvc.perform(put("http://localhost:9898/api/v1/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest()));
    }

    /**
     * Null data test
     * @throws Exception
     */
    @Test
    public void whenPutRequestHasNullData_thenResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = new Cliente(1, null, "Pérez", "CC", 1053,
                33, "Manizales");
        System.out.println(this.mvc.perform(put("http://localhost:9898/api/v1/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest()));
    }
}

