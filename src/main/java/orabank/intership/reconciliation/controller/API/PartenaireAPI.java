package orabank.intership.reconciliation.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.PartenaireDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;


@Api(APP_ROOT+"/Partenaires")
public interface PartenaireAPI {
    @PostMapping(value = APP_ROOT+"/Partenaires/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer un partenaire", notes=" cette methode permet d'enregistrer et modifier un partenaire",response = PartenaireDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le partenaire a ete bien crée ou modifié")
    })
    PartenaireDAO save(@RequestBody PartenaireDAO partenaireDAO);
    @GetMapping(value = APP_ROOT+"/Partenaires/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un partenaire", notes=" cette methode permet de rechercher un partenaire par son id",response = PartenaireDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le partenaire a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "le partenaire n'est pas trouvé dans la base de donnée")
    })
    PartenaireDAO findById(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT+"/Partenaires/findByNom/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un partenaire", notes=" cette methode permet de rechercher un partenaire par son nom",response = PartenaireDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le partenaire a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "le partenaire n'est pas trouvé dans la base de donnée")
    })
    PartenaireDAO findByNom(@PathVariable("nom") String nom);
    @GetMapping(value = APP_ROOT+"/Partenaires/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des partenaires", notes=" cette methode permet de retourner des partenaires ",responseContainer = "List<PartenaireDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des partenaires/liste vide")
    })
    List<PartenaireDAO> findAll();

    @DeleteMapping(value = APP_ROOT+"/Partenaires/delete/{id}")
    void deleteById(@PathVariable("id") Integer id);
}
