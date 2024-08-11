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
public class Repertoire extends AbstractEntity{
    @Column(name = "nom",nullable = false)
    private String nom;
    @ManyToOne
    @JoinColumn(name = "idPartenaire")
    private Partenaire partenaireRep;
}
