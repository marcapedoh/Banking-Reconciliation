package orabank.intership.reconciliation.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.dao.*;
import orabank.intership.reconciliation.repository.ColonneRepository;
import orabank.intership.reconciliation.repository.ExternalDataStructRepository;
import orabank.intership.reconciliation.repository.FileStructRepository;
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
    private final FileStructRepository fileStructRepository;
    private final ColonneRepository colonneRepository;

    @Autowired
    public ReconcialisationServiceImpl(InternalDataStructRepository internalDataStructRepository,ExternalDataStructRepository externalDataStructRepository,FileStructRepository fileStructRepository,ColonneRepository colonneRepository) {
        this.internalDataStructRepository = internalDataStructRepository;
        this.externalDataStructRepository=externalDataStructRepository;
        this.fileStructRepository=fileStructRepository;
        this.colonneRepository=colonneRepository;
    }

    @Override
    public List<String> reconcialisation() {
        var fileStructureDAO= fileStructRepository.findWhereUSeIsTrue().map(FileStructDAO::fromEntity).orElseThrow(()-> new EntityNotFoundException("Aucune structure n'est trouvé dans la base de donné"));
        var internDataDAO=internalDataStructRepository.findAll().stream().map(InternalDataStructDAO::fromEntity).collect(Collectors.toList());

        List<String> messages=new ArrayList<>();
        var externDataDAO=externalDataStructRepository.findAll().stream().map(ExternalDataStructDAO::fromEntity).collect(Collectors.toList());

        var colonnes=colonneRepository.findAllByFileStructId(fileStructureDAO.getId()).stream().map(ColonneDAO::fromEntity).collect(Collectors.toList());
        boolean foundMatch = false;
        StringBuilder errorResult = new StringBuilder();

        for(ColonneDAO colonneDAO:colonnes){
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
        int countSuccessfully=0;
        int countError=0;
        for(String str:messages){
            if(str.contains("Transaction vérifiée")){
                countSuccessfully+=1;
            }else if(str.contains("Erreur! ")){
                countError+=1;
            }

        }
        messages.add("Transactions correct et cohérente: "+countSuccessfully+"\nEt "+countError+"Erreurs trouvés");
        return messages;
    }
}
