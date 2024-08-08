package orabank.intership.reconciliation.controller;


import orabank.intership.reconciliation.controller.API.UtilisateurAPI;
import orabank.intership.reconciliation.dao.UtilisateurDAO;
import orabank.intership.reconciliation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UtilisateurController implements UtilisateurAPI {
    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDAO save(UtilisateurDAO utilisateurDAO) {
        return utilisateurService.save(utilisateurDAO);
    }

    @Override
    public UtilisateurDAO findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDAO findByNom(String nom) {
        return utilisateurService.findByNom(nom);
    }

    @Override
    public List<UtilisateurDAO> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        utilisateurService.deleteById(id);
    }
}
