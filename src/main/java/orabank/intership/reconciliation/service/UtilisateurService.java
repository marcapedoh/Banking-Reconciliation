package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.UtilisateurDAO;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDAO save(UtilisateurDAO user);
    UtilisateurDAO findById(Integer id);
    UtilisateurDAO findByNom(String nom);
    List<UtilisateurDAO> findAll();
    void deleteById(Integer id);
}
