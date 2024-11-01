package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class FindUserById {

    private final IUserRepository IUserRepository;

    public User execute(Long id) throws NotFoundException {
        return IUserRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
