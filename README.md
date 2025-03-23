# 📁 Application de Gestion de Projets et Tâches - Spring Boot

Cette application est une API REST développée avec **Spring Boot** pour gérer des projets et leurs tâches. Elle permet de suivre les priorités, les statuts, les échéances et l’organisation des projets.

---

## 📂 Arborescence du projet

```
src/
└── main/
    └── java/
        └── com/
            └── example/
                └── exo1/
                    ├── controller/
                    │   ├── ProjetController.java
                    │   └── TacheController.java
                    ├── model/
                    │   ├── Priorite.java
                    │   ├── Projet.java
                    │   ├── Statut.java
                    │   └── Tache.java
                    ├── repository/
                    │   ├── ProjetRepository.java
                    │   └── TacheRepository.java
                    ├── service/
                    │   ├── ProjetService.java
                    │   └── TacheService.java
                    └── Exo1Application.java
```

---

## 🧩 Modèles & Énumérations

### ✅ Projet.java
- Entité représentant un projet.
- Champs :
  - `id : int`
  - `nom : String`
  - `description : String`
  - `dateCreation : LocalDateTime`
  - `statut : String`
  - `taches : List<Tache>`

- Annotations utilisées :
  - `@Entity`, `@Table(name = "projet")`
  - `@Getter`, `@Setter`, `@NoArgsConstructor`
  - `@NotBlank`, `@Size`, `@JsonIgnoreProperties`
  - `@OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)`

### ✅ Tache.java
- Entité représentant une tâche associée à un projet.
- Champs :
  - `id : int`
  - `titre : String`
  - `description : String`
  - `statut : Statut (Enum)`
  - `priorite : Priorite (Enum)`
  - `dateCreation : LocalDateTime`
  - `dateEcheance : LocalDateTime`
  - `projet : Projet`

- Annotations utilisées :
  - `@Entity`, `@Table(name = "tache")`
  - `@Enumerated(EnumType.STRING)`
  - `@ManyToOne`, `@JoinColumn(name = "projetId")`

### ✅ Statut.java (Enum)

```java
public enum Statut {
    ÀFAIRE,
    ENCOURS,
    TERMINÉE
}
```

### ✅ Priorite.java (Enum)

```java
public enum Priorite {
    BASSE,
    MOYENNE,
    HAUTE
}
```

---

## 📂 Repositories

### 📌 ProjetRepository.java
- Interface héritant de `JpaRepository<Projet, Integer>`
- Fournit des méthodes CRUD de base

### 📌 TacheRepository.java
- Interface héritant de `JpaRepository<Tache, Integer>`
- Méthodes personnalisées :
  ```java
  List<Tache> findByProjet(long projetId);
  List<Tache> findByStatut(String statut);
  List<Tache> findByPriorite(String priorite);
  Page<Tache> findByProjetId(long projetId, Pageable pageable);
  ```

---

## ⚙️ Services

### 🛠️ ProjetService.java
- Injection de `ProjetRepository`
- Fonctions :
  - `getAllProjets()`
  - `getProjetById(int id)`
  - `addProjet(Projet projet)`
  - `updateProjet(int id, Projet projetDetails)`
  - `deleteProjet(int id)`

### 🛠️ TacheService.java
- Injection de `TacheRepository` et `ProjetRepository`
- Fonctions :
  - `getTacheById(int id)`
  - `getTachesByProjet(long projetId)`
  - `getTachesByProjetPaged(long projetId, Pageable pageable)`
  - `addTache(Tache tache)`
  - `updateTache(int id, Tache tacheDetails)`
  - `deleteTache(int id)`

---

## 📡 Contrôleurs REST

### 🎯 ProjetController.java
- Gère les endpoints liés aux projets :
  - GET `/projets`
  - POST `/projets`
  - PUT `/projets/{id}`
  - DELETE `/projets/{id}`

### 🎯 TacheController.java
- Gère les endpoints liés aux tâches :
  - GET `/taches/{id}`
  - GET `/projets/{projetId}/taches`
  - POST `/taches`
  - PUT `/taches/{id}`
  - DELETE `/taches/{id}`
  - GET `/taches/statut/{statut}`
  - GET `/taches/priorite/{priorite}`
  - GET `/projets/{projetId}/taches/paginated?page=X&size=Y`

---

## ⚙️ Configuration Spring Boot

Fichier `application.properties` :
```properties
spring.application.name=exo1
spring.datasource.url=jdbc:postgresql://localhost:5432/exo1
spring.datasource.username=postgres
spring.datasource.password=##
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

```

---

## ▶️ Lancer l'application

1. Lancer votre base de données Postgres dans mon cas
2. Configurer `application.properties`
3. Exécuter la classe `Exo1Application.java`

---

## 🧪 Exemple d’appel API (via Postman)

- Ajouter un projet :
  ```
  POST /projets
  {
    "nom": "Projet Marketing",
    "description": "Campagne Q2",
    "statut": "En cours"
  }
  ```

- Ajouter une tâche :
  ```
  POST /taches
  {
    "titre": "Préparation du contenu",
    "description": "Rédiger les articles",
    "statut": "ÀFAIRE",
    "priorite": "HAUTE",
    "projet": { "id": 1 }
  }
  ```

---

## 🛠️ Technologies utilisées

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Postgres
- Lombok

---

## 👨‍💻 Auteur

Projet réalisé dans le cadre d’un exercice .
---

## 📬 Contributions

Les contributions sont les bienvenues !