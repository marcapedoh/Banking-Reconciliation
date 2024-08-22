package orabank.intership.reconciliation.validators;

import orabank.intership.reconciliation.dao.ColonneDAO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ColonneValidator {
    public static List<String> validate(ColonneDAO colonneDAO){
        List<String> errors= new ArrayList<>();
        if(colonneDAO==null){
            errors.add("vous passez une colonne vide verifié les données envoyé svp!");
        }
        assert colonneDAO != null;
        if(!StringUtils.hasLength(colonneDAO.getNomColonne())){
            errors.add("la colonne a ciblé doit avoir un nom sinon comment on va la repectorié");
        }

        return errors;
    }
}
