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
    private long refRel;
    @Column(name = "reference",nullable = false)
    private String reference;
    @Column(name = "Date",nullable = false)
    private String date;
    @Column(name = "Type",nullable = false)
    private String type;
    @Column(name = "Montant",nullable = false)
    private double montant;
    @Column(name = "Commission",nullable = false)
    private double commission;
    @Column(name = "Etat",nullable = false)
    private String etat;
    @Column(name = "Compte",nullable = false)
    private String compte;
    @Column(name = "RefApiTierce",nullable = false)
    private String refApiTierce;
    @Column(name = "cmdReference",nullable = false)
    private String commandeRef;
    @Column(name = "RefOperateur",nullable = false)
    private String refOperateur;
    @Column(name = "isSuccess",nullable = false)
    private boolean isSuccess;
    @Column(name = "isConfirmed",nullable = false)
    private boolean isConfirmed;
    @Column(name = "processingSuccess",nullable = false)
    private boolean processingSuccess;
    @Column(name = "checkProcessing",nullable = false)
    private boolean checkProcessing;
    @Column(name = "isCanceledRefunded",nullable = false)
    private boolean isCanceledRefunded;
    @Column(name = "isCanceled",nullable = false)
    private boolean isCanceled;
}
