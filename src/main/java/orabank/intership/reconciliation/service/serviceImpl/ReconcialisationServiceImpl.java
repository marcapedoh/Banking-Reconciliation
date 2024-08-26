package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.ExternalDataStructDAO;
import orabank.intership.reconciliation.dao.InternalDataStructDAO;
import orabank.intership.reconciliation.dao.ReconciliationResponseDAO;
import orabank.intership.reconciliation.repository.ExternalDataStructRepository;
import orabank.intership.reconciliation.repository.InternalDataStructRepository;
import orabank.intership.reconciliation.service.ReconcialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<String> reconcialisation(List<ColonneDAO> colonneDAOS) {
        var internDataDAO=internalDataStructRepository.findAll().stream().map(InternalDataStructDAO::fromEntity).collect(Collectors.toList());

        List<String> messages=new ArrayList<>();
        var externDataDAO=externalDataStructRepository.findAll().stream().map(ExternalDataStructDAO::fromEntity).collect(Collectors.toList());


        boolean foundMatch = false;
        StringBuilder errorResult = new StringBuilder();

        for(ColonneDAO colonneDAO:colonneDAOS){
            switch (colonneDAO.getNomColonne()){
                case "commandeRef":
                    for(InternalDataStructDAO internalDataStructDAO:internDataDAO){
                        boolean isMatched=false;
                        for(ExternalDataStructDAO externalDataStructDAO:externDataDAO){
                            if(Objects.equals(internalDataStructDAO.getCommandeRef(), externalDataStructDAO.getCommandeRef())){
                                foundMatch = true;
                                isMatched = true;
                                messages.add("Transaction vérifiée : CommandRef(Orabank) "+internalDataStructDAO.getCommandeRef()+" - CommandRef(partenaire) "+externalDataStructDAO.getCommandeRef()+"\n");
                            }
                        }
                        if(!isMatched){
                            messages.add("Erreur! CommandRef(partenaire) n'a pas de correspondance pour "+internalDataStructDAO.getCommandeRef()+".\n");
                        }
                    }
                    break;

                case "date":
                    for(InternalDataStructDAO internalDataStructDAO:internDataDAO){
                        boolean isMatched=false;
                        for(ExternalDataStructDAO externalDataStructDAO:externDataDAO){
                            if(Objects.equals(internalDataStructDAO.getDate(), externalDataStructDAO.getDate()) && Objects.equals(internalDataStructDAO.getCommandeRef(), externalDataStructDAO.getCommandeRef())){
                                foundMatch = true;
                                isMatched = true;

                                messages.add("Transaction vérifiée : CommandRef(Orabank) "+"A la Date "+internalDataStructDAO.getDate()+" - CommandId(partenaire) "+"\n");
                                break;
                            }
                        }
                        if (!isMatched) {

                            messages.add("Erreur! Date(partenaire) n'a pas de correspondance pour "+internalDataStructDAO.getDate()+".\n");
                        }
                    }
                    break;
                case "montant":
                    for(InternalDataStructDAO internalDataStructDAO:internDataDAO){
                        boolean isMatched=false;
                        for(ExternalDataStructDAO externalDataStructDAO:externDataDAO){
                            if(internalDataStructDAO.getMontant()==externalDataStructDAO.getMontant() && Objects.equals(internalDataStructDAO.getCommandeRef(), externalDataStructDAO.getCommandeRef())){
                                foundMatch = true;
                                isMatched = true;

                                messages.add("Transaction vérifiée : CommandRef(Orabank) "+internalDataStructDAO.getCommandeRef()+"Avec le montant"+" -CommandRef(partenaire) "+externalDataStructDAO.getCommandeRef()+externalDataStructDAO.getMontant()+"\n");
                                break;
                            }
                        }
                        if (!isMatched) {

                            messages.add("Erreur! Montant(partenaire) et n'a pas de correspondance pour "+internalDataStructDAO.getMontant()+".\n");
                        }
                    }
                    break;
                default:
                    System.out.println("Erreur vérifier le nom des colonnes");
                    break;
            }
        }
        // Renvoyer les résultats
        return messages;
    }
}
