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
public class ExternalDataStruct extends AbstractEntity{
    @Column(name = "CmdReference",nullable = false)
    private String commandeRef;
    @Column(name = "RefRel",nullable = false)
    private String refRel;
    @Column(name = "Reference",nullable = false)
    private String reference;
    @Column(name = "Date",nullable = false)
    private String date;
    @Column(name = "Type",nullable = false)
    private String type;
    @Column(name = "montant",nullable = false)
    private double montant;
    @Column(name = "Commission",nullable = false)
    private double commission;
    @Column(name = "Etat",nullable = false)
    private String etat;
    @Column(name = "Compte",nullable = false)
    private String compte;
    @Column(name = "RefApiTierce",nullable = false)
    private String refApiTierce;
    @Column(name = "RefOperateur",nullable = false)
    private String refOperateur;
    @ManyToOne
    @JoinColumn(name = "idPartenaire")
    private Partenaire partenaireData;
}
