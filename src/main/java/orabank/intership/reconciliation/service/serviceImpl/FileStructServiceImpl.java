package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.dao.FileStructDAO;
import orabank.intership.reconciliation.repository.FileStructRepository;
import orabank.intership.reconciliation.service.FileStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileStructServiceImpl implements FileStructService {

    private final FileStructRepository fileStructRepository;

    @Autowired
    public FileStructServiceImpl(FileStructRepository fileStructRepository) {
        this.fileStructRepository = fileStructRepository;
    }

    @Override
    public FileStructDAO save(FileStructDAO fileStructDAO) {
        assert fileStructDAO!=null;
        fileStructDAO.setUseForConciliation(false);
        return FileStructDAO.fromEntity(fileStructRepository.save(FileStructDAO.toEntity(fileStructDAO)));
    }

    @Override
    public FileStructDAO findById(Integer id) {
        return fileStructRepository.findById(id)
                .map(FileStructDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun element trouvé pour cet id"));
    }

    @Override
    public FileStructDAO changeToUseStructure(Integer structureId) {
       assert structureId!=null;
       var filestructure= fileStructRepository.findById(structureId)
                .map(FileStructDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun element trouvé pour cet id"));

       filestructure.setUseForConciliation(true);
        List<FileStructDAO> fileStructDAOS= fileStructRepository.findAll().stream().map(FileStructDAO::fromEntity).collect(Collectors.toList());
        for(FileStructDAO fileStructDAO:fileStructDAOS){
            if(fileStructDAO.getId()!=filestructure.getId()){
                fileStructDAO.setUseForConciliation(false);
                fileStructRepository.save(FileStructDAO.toEntity(fileStructDAO));
            }
        }
        return FileStructDAO.fromEntity(
                fileStructRepository.save(FileStructDAO.toEntity(filestructure))
        );
    }

    @Override
    public List<FileStructDAO> findAll() {
        return fileStructRepository.findAll().stream()
                .map(FileStructDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        fileStructRepository.deleteById(id);
    }
}
