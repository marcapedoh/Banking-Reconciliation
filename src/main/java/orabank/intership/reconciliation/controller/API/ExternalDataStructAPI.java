package orabank.intership.reconciliation.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import orabank.intership.reconciliation.dao.ColonneDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;


@Api(APP_ROOT+"/DonneesPartenaire")
public interface ExternalDataStructAPI {
    @PostMapping(value = APP_ROOT+"/DonneesPartenaire/saveAll/{sheetAt}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer des données par lots pour les partenaires", notes=" cette methode permet d'enregistrer des données des partenaires",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "les données ont ete bien crée ")
    })
    String saveAll(@RequestPart("file") MultipartFile file, @RequestBody List<ColonneDAO> colonneDAOS,@PathVariable("sheetAt") Integer sheetAt);

    @DeleteMapping(value = APP_ROOT+"/DonneesPartenaire/delete/{id}")
    void deleteById(@PathVariable("id") Integer id);
}