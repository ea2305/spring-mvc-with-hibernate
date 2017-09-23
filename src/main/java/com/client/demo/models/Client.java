/***
 * Modelo usuario, con funciones de entidad para Hibernate con marcado JPA
 * @author Elihu A. Cruz
 * @version 0.1.0
 */

package com.client.demo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Client ")
public class Client implements Serializable {

    // SerialVersion UID
    private static final long serialVersionUID = 1L;

    // Definición de columna, definimos el nombre mediante el parametro "name"
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_client_generator")
    @SequenceGenerator(name="user_client_generator", sequenceName = "user_client_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String phone;

    @Column
    private LocalDate birthdate;


    public Client(){}

    public Client(String first_name, String last_name, String phone, LocalDate birthdate) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
