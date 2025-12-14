package tn.esprit.spring.vticke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.vticke.entities.Evenement;

public interface EvenementRepositories extends JpaRepository<Evenement, Long> {
}
