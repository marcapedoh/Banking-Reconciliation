package orabank.intership.reconciliation.dao;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import orabank.intership.reconciliation.models.Utilisateur;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurDAO {
    private Integer id;
    private String nom;
    private String prenom;
    private String userName;
    private String motDePasse;

    public static UtilisateurDAO fromEntity(Utilisateur utilisateur){
        if(utilisateur==null){
            return null;
        }
        return UtilisateurDAO.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .userName(utilisateur.getUserName())
                .motDePasse(utilisateur.getMotDePasse())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDAO utilisateurDAO){
        if(utilisateurDAO==null){
            return null;
        }
        Utilisateur utilisateur= new Utilisateur();
        utilisateur.setId(utilisateurDAO.getId());
        utilisateur.setNom(utilisateurDAO.getNom());
        utilisateur.setPrenom(utilisateurDAO.getPrenom());
        utilisateur.setUserName(utilisateurDAO.getUserName());
        utilisateur.setMotDePasse(utilisateurDAO.getMotDePasse());
        return utilisateur;
    }
}
