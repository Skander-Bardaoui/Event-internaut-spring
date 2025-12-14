package tn.esprit.spring.vticke.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TicketAspect {

    @AfterThrowing(
            pointcut = "execution(* tn.esprit.spring.vticke.services.*.ajouterTicketsEtAffecterAEvenementEtInternaute(..))",
            throwing = "exception"
    )
    public void afterThrowingException(Exception exception) {
        System.out.println("Le nombre de places restantes dépasse le nombre de tickets demandés");
    }
}