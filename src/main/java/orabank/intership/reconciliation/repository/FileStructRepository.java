package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.FileStruct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStructRepository extends JpaRepository<FileStruct,Integer> {
}
