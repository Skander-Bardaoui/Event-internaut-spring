package tn.esprit.spring.vticke.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Internaute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idInternaute;
    String identifiant;

    @Enumerated(EnumType.STRING)
    TrancheAge trancheAge;


    @OneToMany(mappedBy = "internaute")
    @JsonIgnoreProperties({"internaute", "evenement"})
    List<Ticket> tickets;

}
