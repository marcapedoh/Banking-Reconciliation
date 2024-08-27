package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.dao.FileStructDAO;
import orabank.intership.reconciliation.repository.FileStructRepository;
import orabank.intership.reconciliation.service.FileStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return FileStructDAO.fromEntity(fileStructRepository.save(FileStructDAO.toEntity(fileStructDAO)));
    }

    @Override
    public FileStructDAO findById(Integer id) {
        return null;
    }

    @Override
    public List<FileStructDAO> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
