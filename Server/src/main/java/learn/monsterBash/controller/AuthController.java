package learn.monsterBash.controller;


import learn.monsterBash.domain.UserService;
import learn.monsterBash.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import learn.monsterBash.security.JwtConverter;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/security")
public class AuthController {
    @Autowired
    UserService service;

    AuthenticationManager authenticationManager;
    JwtConverter converter;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){

        try {
            UsernamePasswordAuthenticationToken loginToken =
                    new UsernamePasswordAuthenticationToken( request.getUserName(),
                            request.getPassword());
            Authentication authResult = authenticationManager.authenticate(loginToken);
            if( authResult.isAuthenticated() ){
                UserDetails matchingUser = service.loadUserByUsername(request.getUserName());

                String jwt = converter.getTokenFromUser( matchingUser );
                Map<String, String> fakeReturnObject = new HashMap<>();
                fakeReturnObject.put("jwt", jwt);

                return ResponseEntity.ok(fakeReturnObject);
            } else {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }


        } catch( AuthenticationException ex ){
            //TODO: log this properly
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
