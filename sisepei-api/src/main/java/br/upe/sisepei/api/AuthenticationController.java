package br.upe.sisepei.api;

import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.core.user.model.LoginDTO;
import br.upe.sisepei.core.user.model.RegisterDTO;
import br.upe.sisepei.core.user.useCases.AuthenticateUseCase;
import br.upe.sisepei.core.user.useCases.RegisterUseCase;
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
    private final RegisterUseCase registerUseCase;

    private final AuthenticateUseCase authenticateUseCase;

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @Valid @RequestBody RegisterDTO registerDTO,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasFieldErrors()) {
            throw new UnprocessableEntityException("Error when registering user", bindingResult.getFieldErrors());
        }
        return ResponseEntity.ok(registerUseCase.execute(registerDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
         @Valid @RequestBody LoginDTO request, BindingResult bindingResult
        ) {
        if (bindingResult.hasFieldErrors()) {
            throw new UnprocessableEntityException("Error when logging user", bindingResult.getFieldErrors());
        }
        return ResponseEntity.ok(authenticateUseCase.execute(request));
    }
}
