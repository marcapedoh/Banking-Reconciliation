package orabank.intership.reconciliation.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.ExternalDataStruct;
import orabank.intership.reconciliation.models.Partenaire;
import orabank.intership.reconciliation.models.Repertoire;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepertoireDAO {
    private Integer id;
    private String nom;
    private boolean status;
    private PartenaireDAO partenaireRep;
    @JsonIgnore
    private List<ExternalDataStructDAO> externalDataStructs;

    public static RepertoireDAO fromEntity(Repertoire repertoire){
        if(repertoire==null){
            return null;
        }
        return RepertoireDAO.builder()
                .id(repertoire.getId())
                .status(repertoire.isStatus())
                .nom(repertoire.getNom())
                .partenaireRep(PartenaireDAO.fromEntity(repertoire.getPartenaireRep()))
                .build();
    }

    public static Repertoire toEntity(RepertoireDAO repertoireDAO){
        if(repertoireDAO==null) {
            return null;
        }
        Repertoire repertoire=new Repertoire();
        repertoire.setId(repertoireDAO.getId());
        repertoire.setStatus(repertoireDAO.isStatus());
        repertoire.setNom(repertoireDAO.getNom());
        repertoire.setPartenaireRep(PartenaireDAO.toEntity(repertoireDAO.getPartenaireRep()));
        return  repertoire;
    }
}
