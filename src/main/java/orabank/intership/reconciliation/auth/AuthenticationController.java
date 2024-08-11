package orabank.intership.reconciliation.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static orabank.intership.reconciliation.constant.Utils.APP_ROOT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(APP_ROOT+"/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request,String userRole){
        return ResponseEntity.ok(authenticationService.register(request,userRole));
    }
}
