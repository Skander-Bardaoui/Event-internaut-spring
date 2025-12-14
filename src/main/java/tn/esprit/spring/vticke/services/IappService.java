package tn.esprit.spring.vticke.services;

import tn.esprit.spring.vticke.entities.*;

import java.time.LocalDate;
import java.util.List;

public interface IappService {

    Internaute ajouterInternaute (Internaute internaute) ;

    Evenement ajouterEvenement(Evenement evenement);
    void listeEvenementsParCategorie() ;
    Ticket ajouterTicketEtAffecterEvenementEtInternaute(Ticket ticket, long idEvenement, long idInternaute);
    List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(List<Ticket> tickets, Long idEvenement, Long idInternaute );
    Long nbInternauteParTrancheAgeEtDateEvenement(TrancheAge trancheAge, LocalDate dateMin, LocalDate dateMax);
    Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket);
    String internauteLePlusActif();


}
