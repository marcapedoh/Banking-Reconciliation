package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.RepertoireAPI;
import orabank.intership.reconciliation.dao.RepertoireDAO;
import orabank.intership.reconciliation.service.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RepertoireController implements RepertoireAPI {
    private final RepertoireService repertoireService;
    @Autowired
    public RepertoireController(RepertoireService repertoireService) {
        this.repertoireService = repertoireService;
    }

    @Override
    public RepertoireDAO save(RepertoireDAO repertoireDAO) {
        return repertoireService.save(repertoireDAO);
    }

    @Override
    public List<RepertoireDAO> findAll() {
        return repertoireService.findAll();
    }

    @Override
    public RepertoireDAO findByNom(String nom) {
        return repertoireService.findByNom(nom);
    }

    @Override
    public void deleteById(Integer id) {
        repertoireService.deleteById(id);
    }
}
