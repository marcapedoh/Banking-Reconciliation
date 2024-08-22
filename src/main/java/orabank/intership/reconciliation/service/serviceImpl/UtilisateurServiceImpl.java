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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public UtilisateurDAO save(UtilisateurDAO user) {
        List<String> errors= UtilisateurValidator.validate(user);
        if (!errors.isEmpty()){
            throw new InvalidEntityException("utilisateurnon valide");
        }
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        return UtilisateurDAO.fromEntity(
                utilisateurRepository.save(UtilisateurDAO.toEntity(user))
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
