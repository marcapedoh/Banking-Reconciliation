package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.RepertoireDAO;

import java.util.List;

public interface RepertoireService {
    RepertoireDAO save(RepertoireDAO repertoireDAO);
    List<RepertoireDAO> findAll();
    RepertoireDAO findByNom(String nom);
    void deleteById(Integer id);
}
