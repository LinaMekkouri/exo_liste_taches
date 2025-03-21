package com.example.exo1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Le titre du projet ne peut pas être vide.")
    @Size(max = 255, message = "Le nom de projet ne doit pas dépasser 255 caractères.")
    private String nom;
    @NotBlank(message = "La description du projet ne peut pas être vide.")
    private String description;
    private LocalDateTime dateCreation ;
    private String statut;
    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;
}
