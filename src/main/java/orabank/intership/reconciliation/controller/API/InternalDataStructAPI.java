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


@Api(APP_ROOT+"/DonneesWhatsappBanking")
public interface InternalDataStructAPI {
    @PostMapping(value = APP_ROOT+"/DonneesOrabank/saveAll",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer des données par lots pour les partenaires", notes=" cette methode permet d'enregistrer des données des partenaires",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "les données ont ete bien crée ")
    })
    String saveAll(@RequestPart("file") MultipartFile file);

    @DeleteMapping(value = APP_ROOT+"/DonneesOrabank/delete/{id}")
    void deleteById(@PathVariable("id") Integer id);
}
