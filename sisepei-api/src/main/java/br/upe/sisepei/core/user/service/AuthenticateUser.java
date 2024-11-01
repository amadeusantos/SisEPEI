package br.upe.sisepei.core.user.service;

import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.model.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUser {

    private final IUserRepository IUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse execute(LoginDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = IUserRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}