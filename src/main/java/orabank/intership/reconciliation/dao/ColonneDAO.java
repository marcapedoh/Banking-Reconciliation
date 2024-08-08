package orabank.intership.reconciliation.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.Colonne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColonneDAO {
    private Integer id;
    private int position;
    private String nomColonne;
    private PartenaireDAO partenaire;

    public static ColonneDAO fromEntity(Colonne colonne){
        if(colonne==null){
            return null;
        }
        return ColonneDAO.builder()
                .id(colonne.getId())
                .position(colonne.getPosition())
                .nomColonne(colonne.getNomColonne())
                .partenaire(PartenaireDAO.fromEntity(colonne.getPartenaire()))
                .build();
    }

    public static Colonne toEntity(ColonneDAO colonneDAO){
        if(colonneDAO==null){
            return null;
        }

        Colonne colonne= new Colonne();
        colonne.setId(colonneDAO.getId());
        colonne.setPosition(colonneDAO.getPosition());
        colonne.setNomColonne(colonneDAO.getNomColonne());
        colonne.setPartenaire(PartenaireDAO.toEntity(colonneDAO.getPartenaire()));
        return colonne;
    }
}
