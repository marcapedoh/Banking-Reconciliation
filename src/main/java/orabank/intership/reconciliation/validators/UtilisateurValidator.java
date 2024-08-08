package orabank.intership.reconciliation.validators;

import orabank.intership.reconciliation.dao.UtilisateurDAO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDAO utilisateurDAO){
        List<String> errors= new ArrayList<>();
        if(utilisateurDAO==null){
           errors.add("l'utilisateur que vous passez est vide!");
        }
        assert utilisateurDAO != null;
        if(!StringUtils.hasLength(utilisateurDAO.getNom())){
            errors.add("pardon mettez le nom s'il vous plait c'est important");
        }
        return errors;
    }
}
