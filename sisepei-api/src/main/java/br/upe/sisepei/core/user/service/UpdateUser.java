package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class UpdateUser {
    private final IUserRepository IUserRepository;
    private final FindUserById findUserById;


    public User execute(Long id, UserDTO userDTO) {
        User user = findUserById.execute(id);
        user.setName(userDTO.getName());

        return IUserRepository.save(user);
    }

}
