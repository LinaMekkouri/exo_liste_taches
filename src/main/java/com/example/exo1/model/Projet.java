package com.example.exo1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
@Table(name="projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;
    private LocalDateTime dateCreation ;
    private String statut;
    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;
}
