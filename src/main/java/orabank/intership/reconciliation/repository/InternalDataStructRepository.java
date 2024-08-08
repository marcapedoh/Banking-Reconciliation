package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.InternalDataStruct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternalDataStructRepository extends JpaRepository<InternalDataStruct,Integer> {
}
