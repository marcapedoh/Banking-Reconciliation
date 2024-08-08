package orabank.intership.reconciliation.validators;

import orabank.intership.reconciliation.dao.PartenaireDAO;
import org.apache.catalina.LifecycleState;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PartenaireValidator {

    public static List<String> validate(PartenaireDAO partenaireDAO){
        List<String> errors= new ArrayList<>();
        if(partenaireDAO==null){
            errors.add("le partenaire que vous fournissez n'est pas valide");
        }
        assert partenaireDAO != null;
        if(!StringUtils.hasLength(partenaireDAO.getNom())){
            errors.add("le nom du partenaire est un champs obligatoire!");
        }
        return errors;
    }
}
