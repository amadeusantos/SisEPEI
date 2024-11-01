package br.upe.sisepei.core.user.service.factories;

import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.service.AuthenticateUser;
import org.springframework.security.authentication.AuthenticationManager;

public class AuthenticateUserJPA {

//    public AuthenticateUser handler() {
//        IUserRepository userRepository = UserJPARepository.getInstance();
//        AuthenticationManager authenticationManager = null;
//        JwtService jwtService = new JwtService();
//        return new AuthenticateUser(userRepository, authenticationManager, jwtService);
//    }
}
