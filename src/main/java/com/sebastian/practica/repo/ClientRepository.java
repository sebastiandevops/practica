package com.sebastian.practica.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.practica.model.Cliente;

/**
 * Cliente repository interface to implements JPA.
 * @author sebastian
 *
 */
public interface ClientRepository extends JpaRepository<Cliente, Long> {
    /**
     * Finds idNumber in db.
     * @param idNumber
     */
    Cliente findByIdNumber(Long idNumber);
    Cliente findByAge(int age);
}
