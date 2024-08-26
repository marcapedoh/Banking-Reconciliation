package orabank.intership.reconciliation.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
public class Repertoire extends AbstractEntity{
    @Column(name = "nom",nullable = false)
    private String nom;
    @Column(name = "status",nullable = false)
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "idPartenaire")
    private Partenaire partenaireRep;
    @OneToMany(mappedBy = "repertoire")
    private List<ExternalDataStruct> externalDataStructs;
}
