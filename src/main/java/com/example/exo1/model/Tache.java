package com.example.exo1.model;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tache")
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @NotBlank(message = "Le titre de la tâche ne peut pas être vide.")
    @Size(max = 255, message = "Le titre de la tâche ne doit pas dépasser 255 caractères.")
    private String titre;
    @NotBlank(message = "La description de la tâche ne peut pas être vide.")
    private String description;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Enumerated(EnumType.STRING)
    private Priorite priorite;
    private LocalDateTime dateCreation;
    private LocalDate dateEcheance;
    @ManyToOne
    @JoinColumn(name="projetId", nullable = false)
    private Projet projet;
}
