package com.sebastian.practica.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.practica.model.Photo;
import com.sebastian.practica.repo.PhotoRepository;

/**
 * Photo controller class.
 * @author sebastian
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    private PhotoRepository photoRepo;

    /**
     * Gets all photos
     * @return all photos
     */
    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return photoRepo.findAll();
    }

    /**
     * Gets photo by id
     * @param idNumber
     * @param model
     * @return Requested photo id
     */
    @GetMapping("photos/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getIdNumber());
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "photos" + id;
    }

    /**
     * Gets photo by idNumber
     *
     * @param idNumber
     * @return idNumber or requested photo
     */
    @GetMapping("photos/cc/{idNumber}")
    public ResponseEntity<Photo> findByIdNumber(@PathVariable (value = "idNumber") String idNumber) {
        Optional<Photo> photo = Optional.of(photoRepo.findByIdNumber(idNumber));

        if(photo.isPresent()) {
            return ResponseEntity.ok().body(photo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets upload photo
     * @param model
     * @return uploadPhoto
     */
    @GetMapping("photos/upload")
    public String uploadPhoto(Model model) {
        model.addAttribute("message", "hello");
        return "uploadPhoto";
    }

    /**
     * Posts photo and store it in collection by its idNumber
     *
     * @param idNumber
     * @param image
     * @param model
     * @return route + id
     * @throws IOException
     */
    @PostMapping("photos/add/{idNumber}")
    public String addPhoto(@PathVariable(value = "idNumber") String idNumber,
            @RequestParam("image") MultipartFile image,
            Model model)
                    throws IOException {
        String id = photoService.addPhoto(idNumber, image);
        return "redirect:/photos/" + id;
    }
}