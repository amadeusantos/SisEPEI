package br.upe.sisepei.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.core.user.UserService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    // private final AuthenticationService service;

    private final UserService servico;

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody RegisterRequestDTO request
    ) {
        try{
            return ResponseEntity.ok(servico.register(request));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody AuthenticationRequest request
        ) {
            return ResponseEntity.ok(servico.authenticate(request));
    }
}
