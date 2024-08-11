package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.ErrorCodes;
import orabank.intership.reconciliation.Exception.InvalidEntityException;
import orabank.intership.reconciliation.Exception.InvalidOperationException;
import orabank.intership.reconciliation.constant.DataInitializer;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.repository.ExternalDataStructRepository;
import orabank.intership.reconciliation.service.ExternalDataStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ExternalDataStructServiceImpl implements ExternalDataStructService {
    private final ExternalDataStructRepository externalDataStructRepository;
    private final DataInitializer dataInitializer;

    @Autowired
    public ExternalDataStructServiceImpl(ExternalDataStructRepository externalDataStructRepository,DataInitializer dataInitializer) {
        this.externalDataStructRepository = externalDataStructRepository;
        this.dataInitializer=dataInitializer;
    }

    @Override
    public String saveAll(MultipartFile file,Integer sheetAt,Integer referencePositionAt,Integer montantPosition) {
        if(file.isEmpty()){
            log.warn("le fichier que vous passez en parametre est vide");
            throw new InvalidEntityException("Fichier vide", ErrorCodes.FILE_EMPTY);
        }
        try {
            dataInitializer.saveExcelDataForExternalDataStruct(file.getInputStream(),sheetAt,referencePositionAt,montantPosition);
        }catch (IOException ex){
            log.warn("erreur lors de la lecture du fichier "+ex.getMessage());
        }
        return "Données enregistré avec succès";
    }

    @Override
    public void deleteById(Integer id) {
        assert id!=null;
        externalDataStructRepository.deleteById(id);
    }
}
