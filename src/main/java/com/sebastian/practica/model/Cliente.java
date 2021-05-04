package com.sebastian.practica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Class Cliente
 * @author Sebastián
 *
 */
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class Cliente implements Serializable {

    /**
     * Default constructor
     */
    public Cliente() {

    }
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param idType
     * @param idNumber
     * @param age
     * @param placeOfBirth
     */
    public Cliente(long id, String firstName, String lastName, String idType,
            long idNumber, int age, String placeOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idType = idType;
        this.idNumber = idNumber;
        this.age = age;
        this.placeOfBirth = placeOfBirth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    @Size(min=2, message="First name should have atleast 2 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "id_type", nullable = false)
    private String idType;

    @NaturalId
    @Column(name = "id_number", nullable = false, unique = true)
    @NotNull
    @Size(min=6, message="Id number should have atleast 6 characters")
    private long idNumber;

    @Column(name = "age", nullable = false)
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 99, message = "Age should not be greater than 99")
    private int age;

    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets id number.
     *
     * @return the id number
     */
    public long getIdNumber() {
        return idNumber;
    }

    /**
     * Sets id number.
     *
     * @param the id number
     */
    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Sets id type.
     *
     * @param idType the id type
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * Gets id type.
     *
     * @return the id type
     */
    public String getIdType() {
        return idType;
    }

    /**
     * Gets CLient age.
     *
     * @return the client age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the client age
     *
     * @param Client age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets place of birth
     *
     * @return place of birth
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets place of birth
     *
     * @param placeOfBirth to set
     */
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
}
