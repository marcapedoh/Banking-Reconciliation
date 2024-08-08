package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.ColonneAPI;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.service.ColonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ColonneController implements ColonneAPI {
    private final ColonneService colonneService;

    @Autowired
    public ColonneController(ColonneService colonneService) {
        this.colonneService = colonneService;
    }

    @Override
    public ColonneDAO save(ColonneDAO colonneDAO) {
        return colonneService.save(colonneDAO);
    }

    @Override
    public ColonneDAO findById(Integer id) {
        return colonneService.findById(id);
    }

    @Override
    public ColonneDAO findByNom(String nom) {
        return colonneService.findByNom(nom);
    }

    @Override
    public List<ColonneDAO> findAll() {
        return colonneService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        colonneService.deleteById(id);
    }
}
