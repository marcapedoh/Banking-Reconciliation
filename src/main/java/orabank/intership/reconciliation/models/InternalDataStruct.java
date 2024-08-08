package orabank.intership.reconciliation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
public class InternalDataStruct extends AbstractEntity {
    @Column(name = "referenceId",nullable = false)
    private long referenceId;
    @Column(name = "montant",nullable = false)
    private double montant;
    @Column(name = "commandeRef",nullable = false)
    private String commandeRef;
}
