package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.Exception.ErrorCodes;
import orabank.intership.reconciliation.Exception.InvalidEntityException;
import orabank.intership.reconciliation.Exception.InvalidOperationException;
import orabank.intership.reconciliation.dao.PartenaireDAO;
import orabank.intership.reconciliation.dao.RepertoireDAO;
import orabank.intership.reconciliation.models.Repertoire;
import orabank.intership.reconciliation.repository.PartenaireRepository;
import orabank.intership.reconciliation.repository.RepertoireRepository;
import orabank.intership.reconciliation.service.PartenaireService;
import orabank.intership.reconciliation.validators.PartenaireValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PartenaireServiceImpl implements PartenaireService {
    private final PartenaireRepository partenaireRepository;
    private final RepertoireRepository repertoireRepository;

    @Autowired
    public PartenaireServiceImpl(PartenaireRepository partenaireRepository,RepertoireRepository repertoireRepository) {
        this.partenaireRepository = partenaireRepository;
        this.repertoireRepository= repertoireRepository;
    }

    @Override
    public PartenaireDAO save(PartenaireDAO partenaireDAO) {
        List<String> errors= PartenaireValidator.validate(partenaireDAO);
        if(!errors.isEmpty()){
            log.warn("partenaire non valid voici les informations {}",partenaireDAO);
            throw new InvalidEntityException("Partenaire not valid", ErrorCodes.PARTENAIRE_NOT_FOUND);
        }
        var partenaire=partenaireRepository.save(PartenaireDAO.toEntity(partenaireDAO));
        RepertoireDAO repertoireDAO= RepertoireDAO.builder()
                .nom("REP"+partenaireDAO.getNom()+Math.round(Math.random()*100))
                .partenaireRep(PartenaireDAO.fromEntity(partenaire))
                .status(false)
                .build();
        repertoireRepository.save(RepertoireDAO.toEntity(repertoireDAO));
        return PartenaireDAO.fromEntity(
                partenaire
        );
    }

    @Override
    public PartenaireDAO findById(Integer id) {
        assert id!=null;
        return partenaireRepository.findById(id)
                .map(PartenaireDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun partenaire n'est trouvé avec l'id que vous avez fourni",ErrorCodes.PARTENAIRE_NOT_FOUND));
    }

    @Override
    public PartenaireDAO findByNom(String nom) {
        assert nom!=null;
        return partenaireRepository.findByNom(nom)
                .map(PartenaireDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun partenaire n'est trouvé avec le nom que vous avez fourni",ErrorCodes.PARTENAIRE_NOT_FOUND));
    }

    @Override
    public List<PartenaireDAO> findAll() {
        return partenaireRepository.findAll().stream()
                .map(PartenaireDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        assert id != null;
        List<Repertoire> repertoires=repertoireRepository.findAllByPartenaireRepId(id);
        if( repertoires != null){
            throw new InvalidOperationException("vous ne pouvez pas supprimé ce partenaire qui est relié à un ou plusieurs repertoire");
        }
        partenaireRepository.deleteById(id);
    }
}
