package orabank.intership.reconciliation.repository;

import orabank.intership.reconciliation.models.FileStruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileStructRepository extends JpaRepository<FileStruct,Integer> {

    @Query("select f from FileStruct f where f.useForConciliation=true")
    Optional<FileStruct> findWhereUSeIsTrue();
}
