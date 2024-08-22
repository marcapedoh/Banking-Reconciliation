package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.Exception.ErrorCodes;
import orabank.intership.reconciliation.Exception.InvalidEntityException;
import orabank.intership.reconciliation.dao.RepertoireDAO;
import orabank.intership.reconciliation.repository.RepertoireRepository;
import orabank.intership.reconciliation.service.RepertoireService;
import orabank.intership.reconciliation.validators.RepertoireValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RepertoireServiceImpl implements RepertoireService {

    private final RepertoireRepository repertoireRepository;

    @Autowired
    public RepertoireServiceImpl(RepertoireRepository repertoireRepository) {
        this.repertoireRepository = repertoireRepository;
    }

    @Override
    public RepertoireDAO save(RepertoireDAO repertoireDAO) {
        List<String> errors= RepertoireValidator.validate(repertoireDAO);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("vous passez un repertoire non valide", ErrorCodes.REPERTOIRE_NOT_VALID);
        }
        return RepertoireDAO.fromEntity(
                repertoireRepository.save(RepertoireDAO.toEntity(repertoireDAO))
        );
    }

    @Override
    public List<RepertoireDAO> findAll() {
        return repertoireRepository.findAll().stream()
                .map(RepertoireDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RepertoireDAO findByNom(String nom) {
        assert nom!=null;
        return repertoireRepository.findByNom(nom)
                .map(RepertoireDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun repertoire n'est touvé pour ce partenaire"));
    }

    @Override
    public void deleteById(Integer id) {
        assert id != null;
        repertoireRepository.deleteById(id);
    }
}
