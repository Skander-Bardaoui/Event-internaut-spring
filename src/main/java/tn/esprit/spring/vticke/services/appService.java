package tn.esprit.spring.vticke.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.vticke.entities.*;
import tn.esprit.spring.vticke.repositories.CategorieRepositories;
import tn.esprit.spring.vticke.repositories.EvenementRepositories;
import tn.esprit.spring.vticke.repositories.InternauteRepositories;
import tn.esprit.spring.vticke.repositories.TicketRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Aspect
@Service
@AllArgsConstructor
@Slf4j


public class appService implements IappService {
    InternauteRepositories InternauteRepo;
    EvenementRepositories EvenementRepo;
    CategorieRepositories CategorieRepo;
    TicketRepositories TicketRepo;


    @Override
    public Internaute ajouterInternaute(Internaute internaute) {
        return InternauteRepo.save(internaute);
    }

    @Override
    public Evenement ajouterEvenement(Evenement evenement) {
        return EvenementRepo.save(evenement);
    }

    @Override
    @Scheduled(cron = "*/15 * * * * *")
    public void listeEvenementsParCategorie() {
        List<Categorie> categories = CategorieRepo.findAll();

        for (Categorie categorie : categories) {
            System.out.println("Catégorie: " + categorie.getNomCategorie());

            for (Evenement evenement : categorie.getEvenements()) {
                System.out.println("  - " + evenement.getNomEvenement());
            }
        }
    }

    @Override
    public Ticket ajouterTicketEtAffecterEvenementEtInternaute(Ticket ticket, long idEvenement, long idInternaute) {
        // Get event and internaute
        Evenement evenement = EvenementRepo.findById(idEvenement).orElse(null);
        Internaute internaute = InternauteRepo.findById(idInternaute).orElse(null);

        // Check if enough places available
        if (evenement.getNbPlacesRestant() <= 0) {
            throw new UnsupportedOperationException("nombre de places demandées indisponibe");
        }

        // Assign to ticket
        ticket.setEvenement(evenement);
        ticket.setInternaute(internaute);

        // Decrease available places
        evenement.setNbPlacesRestant(evenement.getNbPlacesRestant() - 1);

        // Save event first (to update places)
        EvenementRepo.save(evenement);

        // Save and return ticket
        return TicketRepo.save(ticket);
    }

    @Override
    public List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(List<Ticket> tickets, Long idEvenement, Long idInternaute) {
        Evenement evenement = EvenementRepo.findById(idEvenement).get();
        Internaute internaute = InternauteRepo.findById(idInternaute).get();

        if (evenement.getNbPlacesRestant() < tickets.size()) {
            throw new UnsupportedOperationException("nombre de places demandées indisponibe");
        }

        for (Ticket ticket : tickets) {
            ticket.setEvenement(evenement);
            ticket.setInternaute(internaute);
        }

        evenement.setNbPlacesRestant(evenement.getNbPlacesRestant() - tickets.size());
        EvenementRepo.save(evenement);

        return TicketRepo.saveAll(tickets);
    }

    @Override
    public Long nbInternauteParTrancheAgeEtDateEvenement(TrancheAge trancheAge, LocalDate dateMin, LocalDate dateMax) {
        LocalDateTime dateTimeMin = dateMin.atStartOfDay();
        LocalDateTime dateTimeMax = dateMax.atTime(23, 59, 59);

        return InternauteRepo.countDistinctByTrancheAgeAndTickets_Evenement_DateEvenementBetween(
                trancheAge,
                dateTimeMin,
                dateTimeMax
        );
    }

    @Override
    public Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket) {
        return TicketRepo.montantRecupereParEvtEtTypeTicket(nomEvt, typeTicket);
    }

    @Override
    public String internauteLePlusActif() {
        List<String> result = InternauteRepo.findInternauteLePlusActif();

        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }
}

