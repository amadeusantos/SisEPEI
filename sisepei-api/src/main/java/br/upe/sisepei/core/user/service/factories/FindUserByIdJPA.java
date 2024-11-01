package br.upe.sisepei.core.user.service.factories;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.service.FindUserById;

public class FindUserByIdJPA {

    public FindUserById handle() {
        IUserRepository userRepository = UserJPARepository.getInstance();
        return new FindUserById(userRepository);
    }
}
