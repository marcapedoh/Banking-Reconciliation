package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.Colonne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColonneRepository extends JpaRepository<Colonne,Integer> {

    Optional<Colonne> findByNomColonne(String nom);
}
