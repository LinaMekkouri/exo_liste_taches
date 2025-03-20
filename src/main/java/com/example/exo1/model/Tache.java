package com.example.exo1.model;
import jakarta.persistence.*;
import jakarta.persistence.Id;
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
    private String titre;
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
