package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.ExternalDataStruct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalDataStructRepository extends JpaRepository<ExternalDataStruct,Integer> {
}
