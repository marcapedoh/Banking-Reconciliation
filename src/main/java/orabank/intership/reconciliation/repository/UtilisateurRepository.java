package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

    Optional<Utilisateur> findByNom(String nom);
    Optional<Utilisateur> findByUserName(String nom);
}
