package orabank.intership.reconciliation.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.Colonne;
import orabank.intership.reconciliation.models.ExternalDataStruct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColonneDAO {
    private Integer id;
    private String nomColonne;
    private PartenaireDAO partenaire;
    private FileStructDAO fileStructDAO;

    public static ColonneDAO fromEntity(Colonne colonne){
        if(colonne==null){
            return null;
        }
        return ColonneDAO.builder()
                .id(colonne.getId())
                .nomColonne(colonne.getNomColonne())
                .partenaire(PartenaireDAO.fromEntity(colonne.getPartenaire()))
                .fileStructDAO(FileStructDAO.fromEntity(colonne.getFileStruct()))
                .build();
    }

    public static Colonne toEntity(ColonneDAO colonneDAO){
        if(colonneDAO==null){
            return null;
        }
        Colonne colonne= new Colonne();
        colonne.setId(colonneDAO.getId());
        colonne.setNomColonne(colonneDAO.getNomColonne());
        colonne.setPartenaire(PartenaireDAO.toEntity(colonneDAO.getPartenaire()));
        colonne.setFileStruct(FileStructDAO.toEntity(colonneDAO.getFileStructDAO()));
        return colonne;
    }

    public static List<ColonneDAO> fromEntities(List<Colonne> colonnes){
        if(colonnes==null){
            return null;
        }
        List<ColonneDAO> colonneDAOS= new ArrayList<>();
        for(Colonne colonne:colonnes){
            colonneDAOS.add(ColonneDAO.builder()
                            .id(colonne.getId())
                            .nomColonne(colonne.getNomColonne())

                            .partenaire(PartenaireDAO.fromEntity(colonne.getPartenaire()))
                    .build());
        }


        return colonneDAOS;
    }

    public static List<Colonne> toEntities(List<ColonneDAO> colonneDAOS){
        if(colonneDAOS==null){
            return null;
        }
        List<Colonne> colonnes= new ArrayList<>();
        for (ColonneDAO colonneDAO:colonneDAOS){
            colonnes.add(Colonne.builder()
                            .nomColonne(colonneDAO.getNomColonne())
                            .partenaire(PartenaireDAO.toEntity(colonneDAO.getPartenaire()))
                    .build());
        }
        return colonnes;
    }
}
