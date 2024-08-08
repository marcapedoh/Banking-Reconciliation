package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.Exception.ErrorCodes;
import orabank.intership.reconciliation.Exception.InvalidEntityException;
import orabank.intership.reconciliation.dao.UtilisateurDAO;
import orabank.intership.reconciliation.repository.UtilisateurRepository;
import orabank.intership.reconciliation.service.UtilisateurService;
import orabank.intership.reconciliation.validators.UtilisateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDAO save(UtilisateurDAO utilisateurDAO) {
        List<String> error= UtilisateurValidator.validate(utilisateurDAO);
        if(!error.isEmpty()){
            log.warn("voici votre utilisateur {}",utilisateurDAO);
            throw new InvalidEntityException("Utilisateur non valide", ErrorCodes.UTILISATEUR_NOT_VALID);
        }
        return UtilisateurDAO.fromEntity(
                utilisateurRepository.save(UtilisateurDAO.toEntity(utilisateurDAO))
        );
    }

    @Override
    public UtilisateurDAO findById(Integer id) {
        assert id!=null;
        return utilisateurRepository.findById(id)
                .map(UtilisateurDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucun utliisateur n'est trouvé dans la base de donnée avec cet id",ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDAO findByNom(String nom) {
        assert nom!=null;
        return utilisateurRepository.findByNom(nom)
                .map(UtilisateurDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucun utliisateur n'est trouvé dans la base de donnée avec cet nom",ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDAO> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        assert id!=null;
        utilisateurRepository.deleteById(id);
    }
}
