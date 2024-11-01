package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUser {

    private final IUserRepository IUserRepository = UserJPARepository.getInstance();

    public void execute(Long id) {
        if (!IUserRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        IUserRepository.deleteById(id);
    }
}
