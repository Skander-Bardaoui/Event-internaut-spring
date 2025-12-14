package tn.esprit.spring.vticke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.vticke.entities.Internaute;
import tn.esprit.spring.vticke.entities.TrancheAge;

import java.time.LocalDateTime;
import java.util.List;

public interface InternauteRepositories extends JpaRepository<Internaute, Long> {

    Long countDistinctByTrancheAgeAndTickets_Evenement_DateEvenementBetween(
            TrancheAge trancheAge,
            LocalDateTime dateMin,
            LocalDateTime dateMax
    );

    @Query("SELECT i.identifiant FROM Internaute i LEFT JOIN i.tickets t GROUP BY i ORDER BY COUNT(t) DESC")
    List<String> findInternauteLePlusActif();
}