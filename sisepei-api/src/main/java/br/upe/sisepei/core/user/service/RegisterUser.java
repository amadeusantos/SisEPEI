package br.upe.sisepei.core.user.service;

import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.repository.UserRepository;
import br.upe.sisepei.core.user.model.RegisterDTO;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse execute(RegisterDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException("Email already registered");
        }
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
