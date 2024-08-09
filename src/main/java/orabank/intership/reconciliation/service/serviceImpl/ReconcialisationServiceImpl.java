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
        StringBuilder result = new StringBuilder();
        boolean foundMatch = false;
        StringBuilder errorResult = new StringBuilder();

        for (ExternalDataStructDAO externalDataStructDAO : externDataDAO) {
            boolean isMatched = false; // Variable pour savoir si une correspondance a été trouvée pour chaque externalDataStructDAO

            for (InternalDataStructDAO internalDataStructDAO : internDataDAO) {
                if (Objects.equals(externalDataStructDAO.getReferenceId(), internalDataStructDAO.getCommandeRef())
                        && externalDataStructDAO.getMontant() == internalDataStructDAO.getMontant()) {
                    foundMatch = true;
                    isMatched = true; // Une correspondance a été trouvée
                    result.append("Transaction vérifiée et conforme: CommandId(Orabank) ")
                            .append(internalDataStructDAO.getCommandeRef())
                            .append(" Montant ").append(internalDataStructDAO.getMontant())
                            .append(" - CommandId(partenaire) ")
                            .append(externalDataStructDAO.getReferenceId())
                            .append(" pour un montant de: ")
                            .append(externalDataStructDAO.getMontant())
                            .append("\n");
                    break; // Sortir de la boucle interne si une correspondance est trouvée
                }
            }

            // Si aucune correspondance n'a été trouvée pour cet externalDataStructDAO
            if (!isMatched) {
                errorResult.append("Erreur! CommandId(partenaire) ")
                        .append(externalDataStructDAO.getReferenceId())
                        .append(" avec Montant ").append(externalDataStructDAO.getMontant())
                        .append(" n'a pas trouvé de correspondance.\n");
            }
        }

        // Renvoyer les résultats
        String finalResult = result.toString() + "\n" + errorResult.toString();
        return finalResult;
    }
}
