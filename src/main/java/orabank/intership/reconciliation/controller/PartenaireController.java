package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.PartenaireAPI;
import orabank.intership.reconciliation.dao.PartenaireDAO;
import orabank.intership.reconciliation.service.PartenaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PartenaireController implements PartenaireAPI {
    private final PartenaireService partenaireService;

    @Autowired
    public PartenaireController(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }

    @Override
    public PartenaireDAO save(PartenaireDAO partenaireDAO) {
        return partenaireService.save(partenaireDAO);
    }

    @Override
    public PartenaireDAO findById(Integer id) {
        return partenaireService.findById(id);
    }

    @Override
    public PartenaireDAO findByNom(String nom) {
        return partenaireService.findByNom(nom);
    }

    @Override
    public List<PartenaireDAO> findAll() {
        return partenaireService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        partenaireService.deleteById(id);
    }
}
