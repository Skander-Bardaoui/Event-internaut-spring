package tn.esprit.spring.vticke.restControllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.vticke.entities.*;
import tn.esprit.spring.vticke.services.IappService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class appRestController {

    IappService appService;

    @PostMapping("/ajouterInternaute")
    public Internaute ajouterInternaute(@RequestBody Internaute internaute) {
        return appService.ajouterInternaute(internaute);
    }

    @PostMapping("/addevent")
    public Evenement ajouterEvenement(@RequestBody Evenement evenement) {
        return appService.ajouterEvenement(evenement);
    }

    @PostMapping("/ajouterTicket/{idEvenement}/{idInternaute}")
    public Ticket ajouterTicket(@RequestBody Ticket ticket, @PathVariable long idEvenement, @PathVariable long idInternaute) {
        return appService.ajouterTicketEtAffecterEvenementEtInternaute(ticket, idEvenement, idInternaute);
    }

    @PostMapping("/ajouterTickets/{idEvenement}/{idInternaute}")
    public List<Ticket> ajouterTickets(@RequestBody List<Ticket> tickets, @PathVariable Long idEvenement, @PathVariable Long idInternaute) {
        return appService.ajouterTicketsEtAffecterAEvenementEtInternaute(tickets, idEvenement, idInternaute);
    }

    @GetMapping("/nbInternautes/{trancheAge}/{dateMin}/{dateMax}")
    public Long nbInternautes(@PathVariable TrancheAge trancheAge,
                              @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateMin,
                              @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateMax) {
        return appService.nbInternauteParTrancheAgeEtDateEvenement(trancheAge, dateMin, dateMax);
    }

    @GetMapping("/montant/{nomEvt}/{typeTicket}")
    public Double montant(@PathVariable String nomEvt, @PathVariable TypeTicket typeTicket) {
        return appService.montantRecupereParEvtEtTypeTicket(nomEvt, typeTicket);
    }

    @GetMapping("/internauteLePlusActif")
    public String internauteLePlusActif() {
        return appService.internauteLePlusActif();
    }
}