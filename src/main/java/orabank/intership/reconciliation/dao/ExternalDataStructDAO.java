package orabank.intership.reconciliation.dao;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.ExternalDataStruct;
import orabank.intership.reconciliation.models.Partenaire;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExternalDataStructDAO {
    private Integer id;
    private String commandeRef;
    private long refRel;
    private String reference;
    private String date;
    private String type;
    private double montant;
    private double commission;
    private String etat;
    private String compte;
    private String refApiTierce;
    private String refOperateur;
    private PartenaireDAO partenaireData;

    public static ExternalDataStructDAO fromEntity(ExternalDataStruct externalDataStruct){
        if(externalDataStruct==null){
            return null;
        }
        return ExternalDataStructDAO.builder()
                .id(externalDataStruct.getId())
                .commandeRef(externalDataStruct.getCommandeRef())
                .refRel(externalDataStruct.getRefRel())
                .reference(externalDataStruct.getReference())
                .date(externalDataStruct.getDate())
                .type(externalDataStruct.getType())
                .commission(externalDataStruct.getCommission())
                .etat(externalDataStruct.getEtat())
                .compte(externalDataStruct.getCompte())
                .refApiTierce(externalDataStruct.getRefApiTierce())
                .refOperateur(externalDataStruct.getRefOperateur())
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
        externalDataStruct.setCommandeRef(externalDataStructDAO.getCommandeRef());
        externalDataStruct.setRefRel(externalDataStructDAO.getRefRel());
        externalDataStruct.setReference(externalDataStructDAO.getReference());
        externalDataStruct.setDate(externalDataStructDAO.getDate());
        externalDataStruct.setType(externalDataStructDAO.getType());
        externalDataStruct.setEtat(externalDataStructDAO.getEtat());
        externalDataStruct.setCompte(externalDataStructDAO.getCompte());
        externalDataStruct.setCommission(externalDataStructDAO.getCommission());
        externalDataStruct.setRefApiTierce(externalDataStructDAO.getRefApiTierce());
        externalDataStruct.setRefOperateur(externalDataStructDAO.getRefOperateur());
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
                            .commandeRef(externalDataStructDAO.getCommandeRef())
                            .refRel(externalDataStructDAO.getRefRel())
                            .reference(externalDataStructDAO.getReference())
                            .date(externalDataStructDAO.getDate())
                            .type(externalDataStructDAO.getType())
                            .etat(externalDataStructDAO.getEtat())
                            .compte(externalDataStructDAO.getCompte())
                            .commission(externalDataStructDAO.getCommission())
                            .refApiTierce(externalDataStructDAO.getRefApiTierce())
                            .refOperateur(externalDataStructDAO.getRefOperateur())
                            .montant(externalDataStructDAO.getMontant())
                            .partenaireData(PartenaireDAO.toEntity(externalDataStructDAO.getPartenaireData()))
                    .build());
        }
        return externalDataStructs;
    }
}
