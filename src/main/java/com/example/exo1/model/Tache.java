package com.example.exo1.model;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor

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

    public Tache(int id, String titre, String description, Statut statut, Priorite priorite, LocalDate dateEcheance, Projet projet) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.statut = statut;
        this.priorite = priorite;
        this.dateEcheance = dateEcheance;
        this.projet = projet;
        this.dateCreation = LocalDateTime.now();

    }
}
