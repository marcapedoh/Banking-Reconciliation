package orabank.intership.reconciliation.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import orabank.intership.reconciliation.dao.ColonneDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;

@Api(APP_ROOT+"/Colonnes")
public interface ColonneAPI {
    @PostMapping(value = APP_ROOT+"/Colonnes/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer une colonne", notes=" cette methode permet d'enregistrer et modifier une colonne",response = ColonneDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la colonne a ete bien crée ou modifié")
    })
    ColonneDAO save(@RequestBody ColonneDAO colonneDAO);
    @GetMapping(value = APP_ROOT+"/Colonnes/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une colonne", notes=" cette methode permet de rechercher une colonne par son id",response = ColonneDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la colonne a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "la colonne n'est pas trouvé dans la base de donnée")
    })
    ColonneDAO findById(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT+"/Colonnes/findByNom/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une colonne", notes=" cette methode permet de rechercher une colonne par son nom",response = ColonneDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la colonne a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "la colonne n'est pas trouvé dans la base de donnée")
    })
    ColonneDAO findByNom(@PathVariable("nom") String nom);
    @GetMapping(value = APP_ROOT+"/Colonnes/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des colonnes", notes=" cette methode permet de retourner des colonnes ",responseContainer = "List<ColonneDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des colonnes/liste vide")
    })
    List<ColonneDAO> findAll();

    @DeleteMapping(value = APP_ROOT+"/Colonnes/delete/{id}")
    void deleteById(@PathVariable("id") Integer id);
}
