package orabank.intership.reconciliation.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.ExternalDataStruct;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExternalDataStructDAO {
    private Integer id;
    private String referenceId;
    private double montant;
    private PartenaireDAO partenaireData;

    public static ExternalDataStructDAO fromEntity(ExternalDataStruct externalDataStruct){
        if(externalDataStruct==null){
            return null;
        }
        return ExternalDataStructDAO.builder()
                .id(externalDataStruct.getId())
                .referenceId(externalDataStruct.getReferenceId())
                .montant(externalDataStruct.getMontant())
                .partenaireData(PartenaireDAO.fromEntity(externalDataStruct.getPartenaireData()))
                .build();
    }
    public static  ExternalDataStruct toEntity(ExternalDataStructDAO externalDataStructDAO){
        if(externalDataStructDAO==null){
            return null;
        }
        ExternalDataStruct externalDataStruct= new ExternalDataStruct();
        externalDataStruct.setId(externalDataStructDAO.getId());
        externalDataStruct.setReferenceId(externalDataStructDAO.getReferenceId());
        externalDataStruct.setMontant(externalDataStructDAO.getMontant());
        externalDataStruct.setPartenaireData(PartenaireDAO.toEntity(externalDataStructDAO.getPartenaireData()));
        return externalDataStruct;
    }

    public static List<ExternalDataStruct> toEntities(List<ExternalDataStructDAO> externalDataStructDAOS){
        if(externalDataStructDAOS==null){
            return null;
        }
        List<ExternalDataStruct> externalDataStructs= new ArrayList<>();
        for(ExternalDataStructDAO externalDataStructDAO:externalDataStructDAOS){
            externalDataStructs.add(ExternalDataStruct.builder()
                            .referenceId(externalDataStructDAO.getReferenceId())
                            .montant(externalDataStructDAO.getMontant())
                            .partenaireData(PartenaireDAO.toEntity(externalDataStructDAO.getPartenaireData()))
                    .build());
        }
        return externalDataStructs;
    }
}
