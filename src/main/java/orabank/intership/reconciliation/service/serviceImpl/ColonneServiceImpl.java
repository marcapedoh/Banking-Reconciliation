package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.Exception.ErrorCodes;
import orabank.intership.reconciliation.Exception.InvalidEntityException;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.repository.ColonneRepository;
import orabank.intership.reconciliation.service.ColonneService;
import orabank.intership.reconciliation.validators.ColonneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ColonneServiceImpl implements ColonneService {
    private final ColonneRepository colonneRepository;

    @Autowired
    public ColonneServiceImpl(ColonneRepository colonneRepository) {
        this.colonneRepository = colonneRepository;
    }

    @Override
    public ColonneDAO save(ColonneDAO colonneDAO) {
        List<String> errors= ColonneValidator.validate(colonneDAO);
        if(!errors.isEmpty()){
            log.warn("voici votre colonne {}",colonneDAO);
            throw new InvalidEntityException("vous passez une colonne nom valide", ErrorCodes.COLONNE_NOT_VALID);
        }
        return ColonneDAO.fromEntity(
                colonneRepository.save(ColonneDAO.toEntity(colonneDAO))
        );
    }

    @Override
    public ColonneDAO findById(Integer id) {
        assert id!=null;
        return colonneRepository.findById(id)
                .map(ColonneDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune colonne trouvé pour cette Id"));
    }

    @Override
    public ColonneDAO findByNom(String nom) {
        assert nom!=null;
        return colonneRepository.findByNomColonne(nom)
                .map(ColonneDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune colonne trouvé pour cette nom"));
    }

    @Override
    public List<ColonneDAO> findAll() {
        return colonneRepository.findAll().stream()
                .map(ColonneDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ColonneDAO> findAllByPartenerNom(String nom) {
        assert nom!=null;
        return colonneRepository.findAllByPartenaireNom(nom).stream()
                .map(ColonneDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        assert id!=null;
        colonneRepository.deleteById(id);
    }
}
