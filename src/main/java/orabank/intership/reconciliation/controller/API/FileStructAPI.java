package orabank.intership.reconciliation.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import orabank.intership.reconciliation.dao.ColonneDAO;
import orabank.intership.reconciliation.dao.FileStructDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;

@Api(APP_ROOT+"/FileStructs")
public interface FileStructAPI {
    @PostMapping(value = APP_ROOT+"/FileStructs/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer une structure de rapprochement", notes=" cette methode permet d'enregistrer et modifier une structure de fichier",response = ColonneDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la structure de fichier a ete bien crée ou modifié")
    })
    FileStructDAO save(@RequestBody FileStructDAO fileStructDAO);
    @GetMapping(value = APP_ROOT+"/FileStructs/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une structure", notes=" cette methode permet de rechercher une structure par son id",response = ColonneDAO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "la strucute a été trouvé dans la base de donnée"),
            @ApiResponse(code=404,message = "la structure n'est pas trouvé dans la base de donnée")
    })
    FileStructDAO findById(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT+"/FileStructs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des structures", notes=" cette methode permet de retourner des structures ",responseContainer = "List<ColonneDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des colonnes/liste vide")
    })
    List<FileStructDAO> findAll();

    @GetMapping(value = APP_ROOT+"/FileStructs/changeToUseStructure/{structureId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des structures", notes=" cette methode permet de retourner des structures ",responseContainer = "List<ColonneDAO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "liste des colonnes/liste vide")
    })
    FileStructDAO changeToUseStructure(@PathVariable("structureId") Integer structureId);

    @DeleteMapping(value = APP_ROOT+"/FileStructs/delete/{id}")
    void deleteById(Integer id);
}
