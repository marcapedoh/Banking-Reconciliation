package orabank.intership.reconciliation.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import orabank.intership.reconciliation.dao.PartenaireDAO;
import orabank.intership.reconciliation.dao.RepertoireDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;


@Api(APP_ROOT+"/Repertoires")
public interface RepertoireAPI {
    @PostMapping(value = APP_ROOT+"/Repertoires/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer un repertoire", notes=" cette methode permet d'enregistrer et modifier un repertoire",response = RepertoireDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le partenaire a ete bien crée ou modifié")
    })
    RepertoireDAO save(@RequestBody RepertoireDAO repertoireDAO);
    @GetMapping(value = APP_ROOT+"/Repertoires/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des repertoires", notes=" cette methode permet de retourner des repertoires ",responseContainer = "List<RepertoireDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des repertoires/liste vide")
    })
    List<RepertoireDAO> findAll();
    @GetMapping(value = APP_ROOT+"/Repertoires/findByNom/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un repertoire", notes="cette methode permet de rechercher un repertoire par son nom",response = PartenaireDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le repertoire a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "le repertoire n'est pas trouvé dans la base de donnée")
    })
    RepertoireDAO findByNom(@PathVariable("nom") String nom);

    @DeleteMapping(value = APP_ROOT+"/Repertoires/delete/{id}")
    void deleteById(@PathVariable("id") Integer id);
}
