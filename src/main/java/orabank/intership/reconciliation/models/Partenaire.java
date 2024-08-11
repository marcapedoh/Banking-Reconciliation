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
public class Partenaire extends AbstractEntity{
    @Column(name = "nom",nullable = false,unique = true)
    private String nom;
    @OneToMany(mappedBy = "partenaire")
    private List<Colonne> colonnes;
    @OneToMany(mappedBy = "partenaireRep")
    private List<Repertoire> repertoires;
    @OneToMany(mappedBy = "partenaireData")
    private List<ExternalDataStruct> externalDataStructs;
}
