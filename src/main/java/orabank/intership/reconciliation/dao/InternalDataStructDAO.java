package orabank.intership.reconciliation.dao;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.InternalDataStruct;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternalDataStructDAO {
    private Integer id;
    private long refRel;
    private String reference;
    private String date;
    private String type;
    private double montant;
    private double commission;
    private String etat;
    private String compte;
    private String refApiTierce;
    private String commandeRef;
    private String refOperateur;
    private boolean isSuccess;
    private boolean isConfirmed;
    private boolean processingSuccess;
    private boolean checkProcessing;
    private boolean isCanceledRefunded;
    private boolean isCanceled;

    public static InternalDataStructDAO fromEntity(InternalDataStruct internalDataStruct){
        if(internalDataStruct==null){
            return null;
        }
        return InternalDataStructDAO.builder()
                .id(internalDataStruct.getId())
                .refRel(internalDataStruct.getRefRel())
                .reference(internalDataStruct.getReference())
                .date(internalDataStruct.getDate())
                .type(internalDataStruct.getType())
                .commission(internalDataStruct.getCommission())
                .etat(internalDataStruct.getEtat())
                .compte(internalDataStruct.getCompte())
                .refApiTierce(internalDataStruct.getRefApiTierce())
                .commandeRef(internalDataStruct.getCommandeRef())
                .refOperateur(internalDataStruct.getRefOperateur())
                .isSuccess(internalDataStruct.isSuccess())
                .isConfirmed(internalDataStruct.isConfirmed())
                .processingSuccess(internalDataStruct.isProcessingSuccess())
                .checkProcessing(internalDataStruct.isCheckProcessing())
                .isCanceled(internalDataStruct.isCanceled())
                .isCanceledRefunded(internalDataStruct.isCanceledRefunded())
                .montant(internalDataStruct.getMontant())
                .build();
    }
    public static InternalDataStruct toEntity(InternalDataStructDAO internalDataStructDAO){
        if(internalDataStructDAO==null){
            return null;
        }
        InternalDataStruct internalDataStruct= new InternalDataStruct();
        internalDataStruct.setId(internalDataStructDAO.getId());
        internalDataStruct.setReference(internalDataStructDAO.getReference());
        internalDataStruct.setRefRel(internalDataStructDAO.getRefRel());
        internalDataStruct.setDate(internalDataStructDAO.getDate());
        internalDataStruct.setType(internalDataStructDAO.getType());
        internalDataStruct.setCommission(internalDataStructDAO.getCommission());
        internalDataStruct.setEtat(internalDataStructDAO.getEtat());
        internalDataStruct.setCompte(internalDataStructDAO.getCompte());
        internalDataStruct.setRefApiTierce(internalDataStructDAO.getRefApiTierce());
        internalDataStruct.setCommandeRef(internalDataStructDAO.getCommandeRef());
        internalDataStruct.setRefOperateur(internalDataStructDAO.getRefOperateur());
        internalDataStruct.setSuccess(internalDataStructDAO.isSuccess());
        internalDataStruct.setConfirmed(internalDataStructDAO.isConfirmed());
        internalDataStruct.setProcessingSuccess(internalDataStructDAO.isProcessingSuccess());
        internalDataStruct.setCheckProcessing(internalDataStructDAO.isCheckProcessing());
        internalDataStruct.setCanceled(internalDataStructDAO.isCanceled());
        internalDataStruct.setCanceledRefunded(internalDataStructDAO.isCanceledRefunded());
        internalDataStruct.setMontant(internalDataStructDAO.getMontant());
        return internalDataStruct;
    }

    public static List<InternalDataStruct> toEntities(List<InternalDataStructDAO> internalDataStructDAOS){
        if(internalDataStructDAOS==null){
            return null;
        }
        List<InternalDataStruct> internalDataStructs= new ArrayList<>();
        for (InternalDataStructDAO internalDataStructDAO:internalDataStructDAOS){
            internalDataStructs.add(InternalDataStruct.builder()
                            .reference(internalDataStructDAO.getReference())
                            .refRel(internalDataStructDAO.getRefRel())
                            .date(internalDataStructDAO.getDate())
                            .type(internalDataStructDAO.getType())
                            .commission(internalDataStructDAO.getCommission())
                            .etat(internalDataStructDAO.getEtat())
                            .compte(internalDataStructDAO.getCompte())
                            .refApiTierce(internalDataStructDAO.getRefApiTierce())
                            .refOperateur(internalDataStructDAO.getRefOperateur())
                            .isSuccess(internalDataStructDAO.isSuccess())
                            .isConfirmed(internalDataStructDAO.isConfirmed())
                            .processingSuccess(internalDataStructDAO.isProcessingSuccess())
                            .checkProcessing(internalDataStructDAO.isCheckProcessing())
                            .isCanceled(internalDataStructDAO.isCanceled())
                            .isCanceledRefunded(internalDataStructDAO.isCanceledRefunded())
                            .commandeRef(internalDataStructDAO.getCommandeRef())
                            .montant(internalDataStructDAO.getMontant())
                    .build());
        }
        return internalDataStructs;
    }
}
