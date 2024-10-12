package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUser {
    private final FindUserById findUserById;
    private final UserRepository userRepository;

    public User execute(Long id, UserDTO userDTO) {
        User user = findUserById.execute(id);
        user.setName(userDTO.getName());

        return userRepository.save(user);
    }

}
