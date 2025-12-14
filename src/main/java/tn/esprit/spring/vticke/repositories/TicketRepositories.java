package tn.esprit.spring.vticke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.vticke.entities.Ticket;
import tn.esprit.spring.vticke.entities.TypeTicket;

public interface TicketRepositories extends JpaRepository<Ticket, Long> {

    @Query("SELECT SUM(t.prixTicket) FROM Ticket t WHERE t.evenement.nomEvenement = :nomEvt AND t.typeTicket = :typeTicket")
    Double montantRecupereParEvtEtTypeTicket(@Param("nomEvt") String nomEvt, @Param("typeTicket") TypeTicket typeTicket);
}
