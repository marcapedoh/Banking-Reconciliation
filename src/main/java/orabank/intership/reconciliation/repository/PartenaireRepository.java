package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartenaireRepository extends JpaRepository<Partenaire,Integer> {

    Optional<Partenaire> findByNom(String nom);
}
