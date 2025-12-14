package tn.esprit.spring.vticke.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idCategorie;
    String codeCategorie;
    String nomCategorie;

    @ManyToMany(mappedBy = "categories")

    List<Evenement> Evenements;

}
