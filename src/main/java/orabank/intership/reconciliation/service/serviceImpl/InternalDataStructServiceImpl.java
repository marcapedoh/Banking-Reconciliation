package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.ErrorCodes;
import orabank.intership.reconciliation.Exception.InvalidEntityException;
import orabank.intership.reconciliation.Exception.InvalidOperationException;
import orabank.intership.reconciliation.constant.DataInitializer;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.repository.InternalDataStructRepository;
import orabank.intership.reconciliation.service.InternalDataStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class InternalDataStructServiceImpl implements InternalDataStructService {
    private final InternalDataStructRepository internalDataStructRepository;
    private final DataInitializer dataInitializer;

    @Override
    public String saveAll(MultipartFile file, List<ColonneDAO> colonneDAOS) {
        if(file.isEmpty()){
            log.warn("le fichier que vous passez en parametre est vide");
            throw new InvalidEntityException("Fichier vide", ErrorCodes.FILE_EMPTY);
        }
        if(colonneDAOS==null){
            throw new InvalidOperationException("les colonnes doivent obligatoirement etre fourni");
        }
        try {
            dataInitializer.saveExcelData(file.getInputStream(),colonneDAOS);
        }catch (IOException ex){
            log.warn("erreur lors de la lecture du fichier "+ex.getMessage());
        }
        return "Données enregistré avec succès";
    }

    @Autowired
    public InternalDataStructServiceImpl(InternalDataStructRepository internalDataStructRepository,DataInitializer dataInitializer) {
        this.internalDataStructRepository = internalDataStructRepository;
        this.dataInitializer=dataInitializer;
    }

    @Override
    public void deleteById(Integer id) {
        assert id!=null;
        internalDataStructRepository.deleteById(id);
    }
}
