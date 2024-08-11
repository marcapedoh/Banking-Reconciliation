package orabank.intership.reconciliation.dao;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.Partenaire;
import orabank.intership.reconciliation.models.Repertoire;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepertoireDAO {
    private Integer id;
    private String nom;
    private PartenaireDAO partenaireRep;

    public static RepertoireDAO fromEntity(Repertoire repertoire){
        if(repertoire==null){
            return null;
        }
        return RepertoireDAO.builder()
                .id(repertoire.getId())
                .nom(repertoire.getNom())
                .partenaireRep(PartenaireDAO.fromEntity(repertoire.getPartenaireRep()))
                .build();
    }

    public static Repertoire toEntity(RepertoireDAO repertoireDAO){
        if(repertoireDAO==null){
            return null;
        }

        Repertoire repertoire=new Repertoire();
        repertoire.setId(repertoireDAO.getId());
        repertoire.setNom(repertoireDAO.getNom());
        repertoire.setPartenaireRep(PartenaireDAO.toEntity(repertoireDAO.getPartenaireRep()));
        return  repertoire;
    }
}
