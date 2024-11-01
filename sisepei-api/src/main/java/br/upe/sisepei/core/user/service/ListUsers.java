package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsers {

    private final IUserRepository IUserRepository = UserJPARepository.getInstance();

    public List<User> execute() {
        return IUserRepository.findAll();
    }
}
