package com.sebastian.practica.controller;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.practica.model.Photo;
import com.sebastian.practica.repo.PhotoRepository;

/**
 * Photo service
 * @author Sebastián
 *
 */
@EnableMongoRepositories(basePackages = "com.sebastian.practica.repo")
@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    /**
     * Adds photo to database
     * @param idNumber
     * @param file
     * @return
     * @throws IOException
     */
    public String addPhoto(String idNumber, MultipartFile file) throws IOException {
        Photo photo = new Photo(idNumber);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        return photo.getId();
    }

    /**
     * Gets photo idNumber
     * @param id
     * @return the idNumber
     */
    public Photo getPhoto(String idNumber) {
        return photoRepo.findById(idNumber).get();
    }
}