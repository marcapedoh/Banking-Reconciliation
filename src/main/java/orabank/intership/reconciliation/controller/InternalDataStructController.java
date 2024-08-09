package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.InternalDataStructAPI;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.service.InternalDataStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class InternalDataStructController implements InternalDataStructAPI {
    private final InternalDataStructService internalDataStructService;

    @Autowired
    public InternalDataStructController(InternalDataStructService internalDataStructService) {
        this.internalDataStructService = internalDataStructService;
    }

    @Override
    public String saveAll(MultipartFile file) {
        return internalDataStructService.saveAll(file);
    }

    @Override
    public void deleteById(Integer id) {
        internalDataStructService.deleteById(id);
    }
}
