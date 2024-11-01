package br.upe.sisepei.core.user.service.factories;

import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.service.RegisterUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUserJPA {

    public RegisterUser handler() {
        IUserRepository userRepository = UserJPARepository.getInstance();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService();
        return new RegisterUser(userRepository, passwordEncoder, jwtService);
    }
}
