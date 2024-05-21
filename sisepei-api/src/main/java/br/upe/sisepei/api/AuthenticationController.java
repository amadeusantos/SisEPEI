package br.upe.sisepei.api;

import br.upe.sisepei.core.user.model.LoginDTO;
import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.core.user.model.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
        @Valid @RequestBody RegisterDTO registerDTO,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(String.join("; ", bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).toList()));
        }
            return ResponseEntity.ok(service.register(registerDTO));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody LoginDTO request
        ) {
            return ResponseEntity.ok(service.authenticate(request));
    }
}
