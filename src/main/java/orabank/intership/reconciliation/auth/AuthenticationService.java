package orabank.intership.reconciliation.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orabank.intership.reconciliation.Exception.EntityNotFoundException;
import orabank.intership.reconciliation.config.JwtService;
import orabank.intership.reconciliation.dao.UtilisateurDAO;
import orabank.intership.reconciliation.models.UserType;
import orabank.intership.reconciliation.models.Utilisateur;
import orabank.intership.reconciliation.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDetails user=utilisateurRepository.findByUserName(request.getUserName()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        String jwtToken="";
        if(passwordEncoder.matches(request.getMotDePasse(),user.getPassword())){
            jwtToken=jwtService.generateToken(user);
        }
        UserDetails user1=utilisateurRepository.findByUserName(request.getUserName()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        if(!StringUtils.hasLength(user1.getUsername())){
            log.warn("le username de ce utilisateur est nul");
        }
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse register(RegisterRequest request,String userRole) {
        var user= Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .roleUser(UserType.valueOf(userRole))
                .userName(request.getUserName())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .build();
        log.warn("voici votre objet{}",user);
        utilisateurRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
