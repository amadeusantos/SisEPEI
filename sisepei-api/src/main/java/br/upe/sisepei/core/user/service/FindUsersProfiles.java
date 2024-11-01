package br.upe.sisepei.core.user.service;

import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class FindUsersProfiles {

    private final IUserRepository IUserRepository;
    private final JwtService jwtService;

    public User execute(String token) throws NotFoundException {
        String email = jwtService.extractUserEmail(token);
        return IUserRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }
}