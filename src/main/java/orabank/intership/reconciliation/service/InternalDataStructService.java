package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.InternalDataStructDAO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InternalDataStructService {
    String saveAll(MultipartFile file);
    void deleteById(Integer id);
}

