package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.ExternalDataStructDAO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExternalDataStructService {
    String saveAll(MultipartFile file, List<ColonneDAO> colonneDAOS,Integer sheetAt);
    void deleteById(Integer id);
}
