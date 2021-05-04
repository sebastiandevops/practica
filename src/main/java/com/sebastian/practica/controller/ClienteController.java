package com.sebastian.practica.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.practica.model.Cliente;
import com.sebastian.practica.repo.ClientRepository;

/**
 * Cliente controller class
 * @author Sebastián
 *
 */
@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClientRepository repo;

    /**
     * Gets all clients in repository
     *
     * @return all clients
     */
    @GetMapping("/clients")
    public List<Cliente> getAllClientes() {
        return repo.findAll();
    }


    @GetMapping
    public List<Cliente> listar() {
        return repo.findAll();
    }

    /**
     * Gets clients by id number
     *
     * @param clientId
     * @return the client by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClientById(@PathVariable(value = "id") Long id) {
        Optional<Cliente> cliente= repo.findById(id);

        if(cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets client by a
     * @param idNumber
     * @return The client
     */
    @GetMapping("/age/{age}")
    public ResponseEntity<Cliente> findByAge(@PathVariable(value = "age") int age) {
        Optional<Cliente> cliente = Optional.of(repo.findByAge(age));

        if(cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets client by idNumber
     * @param idNumber
     * @return The client
     */
    @GetMapping("/cc/{idNumber}")
    public ResponseEntity<Cliente> findByIdNumber(@PathVariable(value = "idNumber") Long idNumber) {
        Optional<Cliente> cliente = Optional.of(repo.findByIdNumber(idNumber));

        if(cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create client
     *
     * @param client
     * @return the client
     */
    @PostMapping("/clients")
    public Cliente createClient(@Validated @RequestBody Cliente cliente) {
        return repo.save(cliente);
    }

    /**
     * Update client response entity
     *
     * @param clientId
     * @param clienteDetails
     * @return the response entity
     * @throws InstanceNotFoundException
     */
    @PutMapping("/update")
    public ResponseEntity<Cliente> updateCliente(
            @Validated @RequestBody Cliente clienteDetails)
                    throws InstanceNotFoundException {
        Cliente cliente = repo
                .findById(clienteDetails.getId())
                .orElseThrow(() -> new InstanceNotFoundException(
                        "Client not found on :: " + clienteDetails.getId()));;
                        cliente.setIdType(clienteDetails.getIdType());
                        cliente.setLastName(clienteDetails.getLastName());
                        cliente.setFirstName(clienteDetails.getFirstName());
                        cliente.setAge(clienteDetails.getAge());
                        cliente.setIdNumber(clienteDetails.getIdNumber());
                        cliente.setPlaceOfBirth(clienteDetails.getPlaceOfBirth());
                        final Cliente updatedCliente = repo.save(cliente);
                        return ResponseEntity.ok(updatedCliente);
    }

    /**
     * Delete client map
     *
     * @param clientId
     * @return the map
     * @throws InstanceNotFoundException
     */
    @DeleteMapping("clients/{idNumber}")
    public Map<String, Boolean> deleteClient(
            @PathVariable(value = "idNumber") Long clientId)
                    throws InstanceNotFoundException {
        Cliente cliente = repo.findById(clientId)
                .orElseThrow(() -> new InstanceNotFoundException(
                        "Client not found on :: " + clientId));
        repo.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
