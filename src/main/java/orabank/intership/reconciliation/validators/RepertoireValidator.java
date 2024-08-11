package orabank.intership.reconciliation.validators;

import orabank.intership.reconciliation.dao.RepertoireDAO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RepertoireValidator {

    public static List<String> validate(RepertoireDAO repertoireDAO){
        List<String> errors= new ArrayList<>();
        if(repertoireDAO==null){
            errors.add("vous passez un repertoire non valide");
        }
        assert repertoireDAO != null;
        if(!StringUtils.hasLength(repertoireDAO.getNom())){
            errors.add("le nom du repertoire ne doit pas etre vide");
        }
        return errors;
    }
}
