package br.upe.sisepei.api;

import br.upe.sisepei.core.user.model.LoginDTO;
import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.core.user.model.RegisterDTO;
import br.upe.sisepei.core.user.service.AuthenticateUser;
import br.upe.sisepei.core.user.service.RegisterUser;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticateUser authenticateUser;
    private final RegisterUser registerUser;

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @Valid @RequestBody RegisterDTO registerDTO,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(String.join("; ", bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).toList()));
        }
            return ResponseEntity.ok(registerUser.execute(registerDTO));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody LoginDTO request
        ) {
            return ResponseEntity.ok(authenticateUser.execute(request));
    }
}
