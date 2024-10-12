package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserRepository;
import br.upe.sisepei.core.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsers {

    private final UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }
}
