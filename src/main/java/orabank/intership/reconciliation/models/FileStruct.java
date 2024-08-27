package orabank.intership.reconciliation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
public class FileStruct extends AbstractEntity{
    @Column(name = "numberOfColumns",nullable = false)
    private Integer numberOfColumns;

    @Column(name = "colonnes",nullable = false)
    @OneToMany(mappedBy = "fileStruct")
    private List<Colonne> colonnes;

}
