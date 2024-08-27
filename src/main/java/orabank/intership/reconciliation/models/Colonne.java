package orabank.intership.reconciliation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
public class Colonne extends AbstractEntity {
    @Column(name = "position",nullable = false)
    private int position;
    @Column(name = "nomColonne",nullable = false)
    private String nomColonne;
    @ManyToOne
    @JoinColumn(name = "idPartenaire")
    private Partenaire partenaire;

    @ManyToOne
    @JoinColumn(name = "idFileStruct")
    private FileStruct fileStruct;
}
