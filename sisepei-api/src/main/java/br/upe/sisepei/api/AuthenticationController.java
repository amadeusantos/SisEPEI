package br.upe.sisepei.api;

import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.core.user.model.LoginDTO;
import br.upe.sisepei.core.user.model.RegisterDTO;
import br.upe.sisepei.core.user.service.AuthenticateUser;
import br.upe.sisepei.core.user.service.RegisterUser;
import br.upe.sisepei.utils.exceptions.UnprocessableEntityException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        if (bindingResult.hasFieldErrors()) {
            throw new UnprocessableEntityException("Error when registering user", bindingResult.getFieldErrors());
        }
        return ResponseEntity.ok(registerUser.execute(registerDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody LoginDTO request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasFieldErrors()) {
            throw new UnprocessableEntityException("Error when logging in user", bindingResult.getFieldErrors());
        }
        return ResponseEntity.ok(authenticateUser.execute(request));
    }
}
