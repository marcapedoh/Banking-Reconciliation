package orabank.intership.reconciliation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
public class InternalDataStruct extends AbstractEntity {
    @Column(name = "refRel",nullable = false)
    private String refRel;
    @Column(name = "reference",nullable = false)
    private String reference;
    @Column(name = "Date",nullable = false)
    private Instant date;
    @Column(name = "Type",nullable = false)
    private String type;
    @Column(name = "Montant",nullable = false)
    private String montant;
    @Column(name = "Commission",nullable = false)
    private String commission;
    @Column(name = "Etat",nullable = false)
    private String Etat;
    @Column(name = "Compte",nullable = false)
    private String Compte;
    @Column(name = "RefApiTierce",nullable = false)
    private String RefApiTierce;
    @Column(name = "cmdReference",nullable = false)
    private String commandeRef;
    @Column(name = "RefOperateur",nullable = false)
    private String refOperateur;
    @Column(name = "isSuccess",nullable = false)
    private String isSuccess;
    @Column(name = "isConfirmed",nullable = false)
    private String isConfirmed;
    @Column(name = "processingSuccess",nullable = false)
    private String processingSuccess;
    @Column(name = "checkProcessing",nullable = false)
    private String checkProcessing;
    @Column(name = "isCanceledRefunded",nullable = false)
    private String isCanceledRefunded;
    @Column(name = "isCanceled",nullable = false)
    private String isCanceled;
}
