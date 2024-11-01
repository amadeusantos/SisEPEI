package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUser {
    private final FindUserById findUserById;
    private final IUserRepository IUserRepository = UserJPARepository.getInstance();

    public User execute(Long id, UserDTO userDTO) {
        User user = findUserById.execute(id);
        user.setName(userDTO.getName());

        return IUserRepository.save(user);
    }

}
