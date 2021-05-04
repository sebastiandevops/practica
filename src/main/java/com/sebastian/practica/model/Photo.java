package com.sebastian.practica.model;

import javax.persistence.Id;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class photo
 * @author Sebastián
 *
 */
@Document(collection = "photos")
public class Photo {

    @Id
    private String id;

    private String idNumber;

    private Binary image;

    /**
     * Constructor
     * @param idNumber
     */
    public Photo(String idNumber) {
        super();
        this.idNumber = idNumber;
    }

    /**
     * Get photo id
     * @return the photo id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets photo id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets photo idNumber
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Sets idNumber
     * @param idNumber
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Gets image
     * @return the image
     */
    public Binary getImage() {
        return image;
    }

    /**
     * Sets image
     * @param image
     */
    public void setImage(Binary image) {
        this.image = image;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return "Photo [id=" + id + ", idNumber=" + idNumber + ", image=" + image + "]";
    }

}