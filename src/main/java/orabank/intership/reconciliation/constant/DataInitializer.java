package orabank.intership.reconciliation.constant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.ExternalDataStructDAO;
import orabank.intership.reconciliation.dao.InternalDataStructDAO;
import orabank.intership.reconciliation.repository.ExternalDataStructRepository;
import orabank.intership.reconciliation.repository.InternalDataStructRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
    public void saveExcelData(InputStream inputStream, List<ColonneDAO> colonnes){
        List<InternalDataStructDAO> internalDataStructDAOS= new ArrayList<>();
        try {
            Workbook workbook= WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row->{
                if (row.getRowNum() == 0) {
                    return; // Ignore la première ligne
                }
                double referenceId= row.getCell(colonnes.get(0).getPosition()).getNumericCellValue();
                double montant= row.getCell(colonnes.get(1).getPosition()).getNumericCellValue();
                String commandeRef=row.getCell(colonnes.get(2).getPosition()).getStringCellValue();
                InternalDataStructDAO internalDataStructDAO= InternalDataStructDAO.builder()
                        .referenceId((long) referenceId)
                        .montant(montant)
                        .commandeRef(commandeRef)
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
    public void saveExcelDataForExternalDataStruct(InputStream inputStream, List<ColonneDAO> colonnes,Integer sheetAt){
        List<ExternalDataStructDAO> externalDataStructDAOS= new ArrayList<>();
        try {
            Workbook workbook= WorkbookFactory.create(inputStream);

            workbook.getSheetAt(sheetAt).forEach(rowExternal->{
                if (rowExternal.getRowNum() == 0) {
                    return;
                }
                String referenceId= rowExternal.getCell(colonnes.get(0).getPosition()).getStringCellValue();
                double montant= rowExternal.getCell(colonnes.get(1).getPosition()).getNumericCellValue();
                ExternalDataStructDAO externalDataStructDAO= ExternalDataStructDAO.builder()
                        .referenceId(referenceId)
                        .montant(montant)
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
