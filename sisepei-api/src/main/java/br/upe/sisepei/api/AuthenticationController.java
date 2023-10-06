package br.upe.sisepei.api;

import br.upe.sisepei.core.user.model.LoginDTO;
import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.core.user.model.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.core.user.UserService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody RegisterDTO request
    ) {
        try{
            return ResponseEntity.ok(service.register(request));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody LoginDTO request
        ) {
            return ResponseEntity.ok(service.authenticate(request));
    }
}
