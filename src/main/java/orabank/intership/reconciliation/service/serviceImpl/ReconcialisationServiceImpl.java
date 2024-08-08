package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.dao.ExternalDataStructDAO;
import orabank.intership.reconciliation.dao.InternalDataStructDAO;
import orabank.intership.reconciliation.repository.ExternalDataStructRepository;
import orabank.intership.reconciliation.repository.InternalDataStructRepository;
import orabank.intership.reconciliation.service.ReconcialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ReconcialisationServiceImpl implements ReconcialisationService {
    private final InternalDataStructRepository internalDataStructRepository;
    private final ExternalDataStructRepository externalDataStructRepository;

    @Autowired
    public ReconcialisationServiceImpl(InternalDataStructRepository internalDataStructRepository,ExternalDataStructRepository externalDataStructRepository) {
        this.internalDataStructRepository = internalDataStructRepository;
        this.externalDataStructRepository=externalDataStructRepository;
    }

    @Override
    public String reconcialisation() {
        var internDataDAO=internalDataStructRepository.findAll().stream().map(InternalDataStructDAO::fromEntity).collect(Collectors.toList());
        var externDataDAO=externalDataStructRepository.findAll().stream().map(ExternalDataStructDAO::fromEntity).collect(Collectors.toList());
        for(ExternalDataStructDAO externalDataStructDAO:externDataDAO){
            for (InternalDataStructDAO internalDataStructDAO:internDataDAO){
                if(Objects.equals(externalDataStructDAO.getReferenceId(), internalDataStructDAO.getCommandeRef()) && externalDataStructDAO.getMontant()==internalDataStructDAO.getMontant()){
                    return "transaction vérifié et conforme commandId(Orabank) "+internalDataStructDAO.getCommandeRef()+" - CommandId(partenaire)"+externalDataStructDAO.getReferenceId() +"pour un montant de: "+externalDataStructDAO.getMontant();
                }else{
                    return "erreur erreur! vérifier vos transactions il y a une erreur le numero de commande n'est pas trouvé ou le montant est différent! voici les données: (commandRefOrabank)" +internalDataStructDAO.getCommandeRef()+" Montant "+internalDataStructDAO.getMontant()+"(commandRefPartenaire) "+externalDataStructDAO.getReferenceId()+" Montant "+externalDataStructDAO.getMontant();
                }
            }
        }
        return "Fin!";
    }
}
