package br.upe.sisepei.core.user.service.factories;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.service.DeleteUser;

public class DeleteUserJPA {

    public DeleteUser handle() {
        IUserRepository userRepository = UserJPARepository.getInstance();
        return new DeleteUser(userRepository);
    }
}
