package orabank.intership.reconciliation.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.InternalDataStruct;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternalDataStructDAO {
    private Integer id;
    private long referenceId;
    private double montant;
    private String commandeRef;

    public static InternalDataStructDAO fromEntity(InternalDataStruct internalDataStruct){
        if(internalDataStruct==null){
            return null;
        }
        return InternalDataStructDAO.builder()
                .id(internalDataStruct.getId())
                .referenceId(internalDataStruct.getReferenceId())
                .montant(internalDataStruct.getMontant())
                .commandeRef(internalDataStruct.getCommandeRef())
                .build();
    }
    public static InternalDataStruct toEntity(InternalDataStructDAO internalDataStructDAO){
        if(internalDataStructDAO==null){
            return null;
        }
        InternalDataStruct internalDataStruct= new InternalDataStruct();
        internalDataStruct.setId(internalDataStructDAO.getId());
        internalDataStruct.setReferenceId(internalDataStructDAO.getReferenceId());
        internalDataStruct.setMontant(internalDataStructDAO.getMontant());
        internalDataStruct.setCommandeRef(internalDataStructDAO.getCommandeRef());
        return internalDataStruct;
    }

    public static List<InternalDataStruct> toEntities(List<InternalDataStructDAO> internalDataStructDAOS){
        if(internalDataStructDAOS==null){
            return null;
        }
        List<InternalDataStruct> internalDataStructs= new ArrayList<>();
        for (InternalDataStructDAO internalDataStructDAO:internalDataStructDAOS){
            internalDataStructs.add(InternalDataStruct.builder()
                            .commandeRef(internalDataStructDAO.getCommandeRef())
                            .montant(internalDataStructDAO.getMontant())
                            .referenceId(internalDataStructDAO.getReferenceId())
                    .build());
        }
        return internalDataStructs;
    }
}
