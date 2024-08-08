package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.UtilisateurDAO;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDAO save(UtilisateurDAO utilisateurDAO);
    UtilisateurDAO findById(Integer id);
    UtilisateurDAO findByNom(String nom);
    List<UtilisateurDAO> findAll();
    void deleteById(Integer id);
}
