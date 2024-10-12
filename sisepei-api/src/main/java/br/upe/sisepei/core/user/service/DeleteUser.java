package br.upe.sisepei.core.user.service;

import br.upe.sisepei.core.user.repository.UserRepository;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUser {

    private final UserRepository userRepository;

    public void execute(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }
}
