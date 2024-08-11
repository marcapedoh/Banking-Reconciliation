package orabank.intership.reconciliation.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.Colonne;
import orabank.intership.reconciliation.models.ExternalDataStruct;
import orabank.intership.reconciliation.models.Partenaire;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartenaireDAO {
    private Integer id;
    private String nom;
    @JsonIgnore
    private List<Colonne> colonnes;
    @JsonIgnore
    private List<ExternalDataStruct> externalDataStructs;

    public static PartenaireDAO fromEntity(Partenaire partenaire){
        if(partenaire==null){
            return null;
        }
        return PartenaireDAO.builder()
                .id(partenaire.getId())
                .nom(partenaire.getNom())
                .build();
    }

    public static Partenaire toEntity(PartenaireDAO partenaireDAO){
        if(partenaireDAO==null){
            return null;
        }
        Partenaire partenaire= new Partenaire();
        partenaire.setId(partenaireDAO.getId());
        partenaire.setNom(partenaireDAO.getNom());
        return partenaire;
    }

}
