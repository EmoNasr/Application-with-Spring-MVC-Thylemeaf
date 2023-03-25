package com.example.hybernatetp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data    //ADD getter et setter
@NoArgsConstructor
@AllArgsConstructor //ADD constructor without parameter frameWork Lombok

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private Date dateNaissance;
    private boolean malade;
    private int score;

}
