package com.sebastian.practica.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.sebastian.practica.model.Photo;

/**
 * Photo repository interface to implements mongodb.
 * @author sebastian
 *
 */
@Component
public interface PhotoRepository extends MongoRepository<Photo, String> {
    /**
     * Finds idNumber
     * @param idNumber
     */
    Photo findByIdNumber(String idNumber);
}