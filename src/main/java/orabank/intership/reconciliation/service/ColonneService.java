package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.ColonneDAO;

import java.util.List;

public interface ColonneService {
    ColonneDAO save(ColonneDAO colonneDAO);
    ColonneDAO findById(Integer id);
    ColonneDAO findByNom(String nom);
    List<ColonneDAO> findAll();
    void deleteById(Integer id);
}
