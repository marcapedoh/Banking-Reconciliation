package orabank.intership.reconciliation.controller;

import orabank.intership.reconciliation.controller.API.FileStructAPI;
import orabank.intership.reconciliation.dao.FileStructDAO;
import orabank.intership.reconciliation.service.FileStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FileStructController implements FileStructAPI {
    @Override
    public FileStructDAO changeToUseStructure(Integer structureId) {
        return fileStructService.changeToUseStructure(structureId);
    }

    private final FileStructService fileStructService;

    @Autowired
    public FileStructController(FileStructService fileStructService) {
        this.fileStructService = fileStructService;
    }

    @Override
    public FileStructDAO save(FileStructDAO fileStructDAO) {
        return fileStructService.save(fileStructDAO);
    }

    @Override
    public FileStructDAO findById(Integer id) {
        return fileStructService.findById(id);
    }

    @Override
    public List<FileStructDAO> findAll() {
        return fileStructService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        fileStructService.deleteById(id);
    }
}
