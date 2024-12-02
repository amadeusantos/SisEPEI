package br.upe.sisepei.core.user.useCases;

import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsersUseCase {

    private final IUserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }
}
