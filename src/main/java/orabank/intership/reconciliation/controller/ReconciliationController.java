package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.ReconciliationAPI;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.ReconciliationResponseDAO;
import orabank.intership.reconciliation.service.ReconcialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReconciliationController implements ReconciliationAPI {
    private final ReconcialisationService reconcialisationService;

    @Autowired
    public ReconciliationController(ReconcialisationService reconcialisationService) {
        this.reconcialisationService = reconcialisationService;
    }

    @Override
    public List<String> reconcialisation(List<ColonneDAO> colonneDAOS) {
        return reconcialisationService.reconcialisation(colonneDAOS);
    }
}
