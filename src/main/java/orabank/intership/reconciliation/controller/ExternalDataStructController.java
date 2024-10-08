package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.ExternalDataStructAPI;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.service.ExternalDataStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ExternalDataStructController implements ExternalDataStructAPI {
    private final ExternalDataStructService externalDataStructService;

    @Autowired
    public ExternalDataStructController(ExternalDataStructService externalDataStructService) {
        this.externalDataStructService = externalDataStructService;
    }

    @Override
    public String saveAll(MultipartFile file,Integer partenaireId) {
        return externalDataStructService.saveAll(file,partenaireId);
    }

    @Override
    public void deleteById(Integer id) {
        externalDataStructService.deleteById(id);
    }

    @Override
    public void deleteAllDataStruct() { externalDataStructService.deleteAllDataStruct(); }
}
