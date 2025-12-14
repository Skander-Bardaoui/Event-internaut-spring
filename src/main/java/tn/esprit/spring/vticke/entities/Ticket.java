package tn.esprit.spring.vticke.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idTicket;
    String codeTicket;
    Double prixTicket;

    @Enumerated(EnumType.STRING)
    TypeTicket typeTicket;


    @ManyToOne
    @JsonIgnoreProperties({"tickets"})
    Internaute internaute;

    @ManyToOne
    @JsonIgnoreProperties({"tickets"})
    Evenement evenement;
}
