package br.upe.sisepei.core.user.service;

import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.repository.UserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUsersProfiles {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User execute(String token) throws NotFoundException {
        String email = jwtService.extractUserEmail(token);
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }
}