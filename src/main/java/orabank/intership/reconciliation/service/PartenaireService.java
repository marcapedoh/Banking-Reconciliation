package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.PartenaireDAO;

import java.util.List;

public interface PartenaireService {
    PartenaireDAO save(PartenaireDAO partenaireDAO);
    PartenaireDAO findById(Integer id);
    PartenaireDAO findByNom(String nom);
    List<PartenaireDAO> findAll();
    void deleteById(Integer id);
}
