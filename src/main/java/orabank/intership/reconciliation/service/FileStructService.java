package orabank.intership.reconciliation.service;

import orabank.intership.reconciliation.dao.FileStructDAO;

import java.util.List;

public interface FileStructService {
    FileStructDAO save(FileStructDAO fileStructDAO);
    FileStructDAO findById(Integer id);
    FileStructDAO changeToUseStructure(Integer structureId);
    List<FileStructDAO> findAll();
    void deleteById(Integer id);
}
