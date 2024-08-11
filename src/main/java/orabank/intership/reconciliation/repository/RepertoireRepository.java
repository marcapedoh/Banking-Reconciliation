package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepertoireRepository extends JpaRepository<Repertoire,Integer> {

    Optional<Repertoire> findByNom(String nom);
    List<Repertoire> findAllByPartenaireRepId(Integer id);
}
