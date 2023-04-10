package br.upe.sisepei.sisepei.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.config.JwtService;
import br.upe.sisepei.sisepei.core.usuario.UsuarioRepositorio;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.sisepei.core.usuario.modelo.UsuarioDTO;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UsuarioRepositorio repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(UsuarioDTO request) {
        if(repository.existsByEmail(request.getEmail())){
            System.out.println("Usuário já cadastrado");
            return AuthenticationResponse.builder()
                .build();
        } else{
            var user = Usuario.builder()
               .nome(request.getNome())
               .email(request.getEmail())
               .senha(passwordEncoder.encode(request.getSenha()))
               .build();
           repository.save(user);
           var jwtToken = jwtService.generateToken(user);
           return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
        }
        }
        
        public AuthenticationResponse authenticate(AuthenticationRequest request) {
            System.out.println(request);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));
            var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    
}
