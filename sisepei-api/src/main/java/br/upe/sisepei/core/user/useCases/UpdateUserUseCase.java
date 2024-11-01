package br.upe.sisepei.core.user.useCases;

import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final IUserRepository userRepository;

    private FindUserByIdUseCase findUserByIdUseCase;

    public User execute(Long id, UserDTO userDTO) {
        User user = findUserByIdUseCase.execute(id);
        user.setName(userDTO.getName());
        return userRepository.save(user);
    }
}
