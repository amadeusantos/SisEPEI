package br.upe.sisepei.core.user.useCases;

import br.upe.sisepei.core.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final IUserRepository userRepository;

    private final FindUserByIdUseCase findUserByIdUseCase;

    public void execute(Long id) {
        findUserByIdUseCase.execute(id);
        userRepository.deleteById(id);
    }
}
