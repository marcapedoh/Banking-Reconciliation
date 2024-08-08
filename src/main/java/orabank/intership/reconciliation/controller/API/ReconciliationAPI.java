package orabank.intership.reconciliation.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import orabank.intership.reconciliation.dao.PartenaireDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;


@Api(APP_ROOT+"/Rapprochements")
public interface ReconciliationAPI {
    @GetMapping(value = APP_ROOT+"/Rapprochements/RapprochementTest",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Effectue des rapprochements", notes=" cette methode permet de faire des rapprochement",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "le rapprochement a été bien effectué dans la base de donnée"),
    })
    String reconcialisation();
}
