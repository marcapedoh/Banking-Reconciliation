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
    @Column(name = "referenceId",nullable = false)
    private String referenceId;
    @Column(name = "montant",nullable = false)
    private double montant;
    @ManyToOne
    @JoinColumn(name = "idPartenaire")
    private Partenaire partenaireData;
}
