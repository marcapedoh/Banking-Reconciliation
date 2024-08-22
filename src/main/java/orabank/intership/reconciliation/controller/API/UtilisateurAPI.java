package orabank.intership.reconciliation.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import orabank.intership.reconciliation.dao.PartenaireDAO;
import orabank.intership.reconciliation.dao.UtilisateurDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;


@Api(APP_ROOT+"/Utilisateurs")
public interface UtilisateurAPI {
    @GetMapping(value = APP_ROOT+"/Utilisateurs/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur", notes=" cette methode permet de rechercher un utilisateur par son id",response = UtilisateurDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "l'utilisateur a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "l'utilisateur n'est pas trouvé dans la base de donnée")
    })
    UtilisateurDAO findById(@PathVariable("id") Integer id);
    @PostMapping(value = APP_ROOT+"/Utilisateurs/create",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "créer un utilisateur", notes=" cette methode permet de creer un utilisateur",response = UtilisateurDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "l'utilisateur a été trouvé dans la base de donnée"),
    })
    UtilisateurDAO save(@RequestBody UtilisateurDAO user);
    @GetMapping(value = APP_ROOT+"/Utilisateurs/findByNom/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur", notes=" cette methode permet de rechercher un utilisateur par son nom",response = UtilisateurDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "l'utilisateur a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "l'utilisateur n'est pas trouvé dans la base de donnée")
    })
    UtilisateurDAO findByNom(@PathVariable("nom") String nom);
    @GetMapping(value = APP_ROOT+"/Utilisateurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des utilisateurs", notes=" cette methode permet de retourner des utilisateurs ",responseContainer = "List<UtilisateurDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des utilisateurs/liste vide")
    })
    List<UtilisateurDAO> findAll();

    @DeleteMapping(value = APP_ROOT+"/Utilisateurs/delete/{id}")
    void deleteById(@PathVariable("id") Integer id);
}
