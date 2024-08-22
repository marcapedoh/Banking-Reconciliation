package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.ReconciliationResponseDAO;

import java.util.List;

public interface ReconcialisationService {
    List<String> reconcialisation(List<ColonneDAO> colonneDAOS);
}
