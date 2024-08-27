package orabank.intership.reconciliation.constant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.dao.*;
import orabank.intership.reconciliation.repository.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer {
    private final ExternalDataStructRepository externalDataStructRepository;
    private final InternalDataStructRepository internalDataStructRepository;
    private final PartenaireRepository partenaireRepository;
    private final RepertoireRepository repertoireRepository;
    public void saveExcelData(InputStream inputStream){
        List<InternalDataStructDAO> internalDataStructDAOS= new ArrayList<>();
        try {
            Workbook workbook= WorkbookFactory.create(inputStream);
            workbook.getSheetAt(0).forEach(row->{
                if (row.getRowNum() == 0) {
                    return; // Ignore la première ligne
                }
                long refRel= (long) row.getCell(0).getNumericCellValue();
                //String reference= row.getCell(1).getStringCellValue();
                String date= row.getCell(2).getStringCellValue();
                String type= row.getCell(3).getStringCellValue();
                double montant= row.getCell(4).getNumericCellValue();
                double commission= row.getCell(5).getNumericCellValue();
                String etat= row.getCell(6).getStringCellValue();
                String compte= row.getCell(7).getStringCellValue();
                String refApiTierce= row.getCell(8).getStringCellValue();
                String commandeRef= row.getCell(9).getStringCellValue();
                String refOperateur= row.getCell(10).getStringCellValue();
                boolean isSuccess= row.getCell(11).getBooleanCellValue();
                boolean isConfirmed= row.getCell(12).getBooleanCellValue();
                boolean processingSuccess= row.getCell(13).getBooleanCellValue();
                boolean checkProcessing= row.getCell(14).getBooleanCellValue();
                boolean isCanceledRefunded= row.getCell(15).getBooleanCellValue();
                boolean isCanceled= row.getCell(16).getBooleanCellValue();
                InternalDataStructDAO internalDataStructDAO= InternalDataStructDAO.builder()
                        .refRel(refRel)
                        //.reference(reference)
                        .date(date)
                        .type(type)
                        .montant(montant)
                        .commission(commission)
                        .etat(etat)
                        .compte(compte)
                        .refApiTierce(refApiTierce)
                        .commandeRef(commandeRef)
                        .refOperateur(refOperateur)
                        .isSuccess(isSuccess)
                        .isConfirmed(isConfirmed)
                        .processingSuccess(processingSuccess)
                        .checkProcessing(checkProcessing)
                        .isCanceledRefunded(isCanceledRefunded)
                        .isCanceled(isCanceled)
                        .build();
                internalDataStructDAOS.add(internalDataStructDAO);
            });
            internalDataStructRepository.saveAll(InternalDataStructDAO.toEntities(internalDataStructDAOS));
            workbook.close();
            log.info("donnée enregistrer avec succes depuis le fichier excel");
        }catch (IOException io){
            log.error("Erreur lors du passage du fichier excel");
        }
    }
    public void saveExcelDataForExternalDataStruct(InputStream inputStream,Integer partenaireId){
        List<ExternalDataStructDAO> externalDataStructDAOS= new ArrayList<>();

        var partenaire=partenaireRepository.findById(partenaireId).map(PartenaireDAO::fromEntity).orElseThrow(()-> new EntityNotFoundException("aucun partenaire trouver pour cette id"));
        var repertoire= repertoireRepository.findByPartenaireRepId(partenaire.getId()).map(RepertoireDAO::fromEntity).orElseThrow(()-> new EntityNotFoundException("Aucun repertoire n'est trouvé pour cet id"));
        try {
            Workbook workbook= WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(rowExternal->{
                if (rowExternal.getRowNum() == 0) {
                    return;
                }
                String commandRef= rowExternal.getCell(0).getStringCellValue();
                long refRel= (long) rowExternal.getCell(1).getNumericCellValue();
                String reference= rowExternal.getCell(2).getStringCellValue();
                String date= rowExternal.getCell(3).getStringCellValue();
                String type= rowExternal.getCell(4).getStringCellValue();
                double montant= rowExternal.getCell(5).getNumericCellValue();
                double commission= rowExternal.getCell(6).getNumericCellValue();
                String etat= rowExternal.getCell(7).getStringCellValue();
                String compte= rowExternal.getCell(8).getStringCellValue();
                String refApiTierce= rowExternal.getCell(9).getStringCellValue();
                String refOperateur=  rowExternal.getCell(10).getStringCellValue();
                ExternalDataStructDAO externalDataStructDAO= ExternalDataStructDAO.builder()
                        .commandeRef(commandRef)
                        .refRel(refRel)
                        .reference(reference)
                        .date(date)
                        .type(type)
                        .montant(montant)
                        .commission(commission)
                        .etat(etat)
                        .compte(compte)
                        .refApiTierce(refApiTierce)
                        .refOperateur(refOperateur)
                        .partenaireData(partenaire)
                        .montant(montant)
                        .repertoire(repertoire)
                        .build();
                externalDataStructDAOS.add(externalDataStructDAO);
            });
            externalDataStructRepository.saveAll(ExternalDataStructDAO.toEntities(externalDataStructDAOS));
            workbook.close();
            log.info("donnée enregistrer avec succes depuis le fichier excel");
        }catch (IOException io){
            log.error("Erreur lors du passage du fichier excel");
        }
    }



}
