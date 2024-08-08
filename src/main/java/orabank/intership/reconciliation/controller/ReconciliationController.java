package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.ReconciliationAPI;
import orabank.intership.reconciliation.service.ReconcialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ReconciliationController implements ReconciliationAPI {
    private final ReconcialisationService reconcialisationService;

    @Autowired
    public ReconciliationController(ReconcialisationService reconcialisationService) {
        this.reconcialisationService = reconcialisationService;
    }

    @Override
    public String reconcialisation() {
        return reconcialisationService.reconcialisation();
    }
}
