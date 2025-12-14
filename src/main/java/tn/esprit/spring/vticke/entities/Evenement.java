package tn.esprit.spring.vticke.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
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
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEvenement;
    String nomEvenement;
        long nbPlacesRestant;
    LocalDateTime dateEvenement;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Categorie> categories;

    @OneToMany(mappedBy = "evenement")
    @JsonIgnoreProperties({"evenement", "internaute"})
    List<Ticket> tickets;
}
