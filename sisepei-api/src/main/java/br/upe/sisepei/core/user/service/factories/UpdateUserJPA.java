package br.upe.sisepei.core.user.service.factories;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.service.FindUserById;
import br.upe.sisepei.core.user.service.UpdateUser;
public class UpdateUserJPA {

    public UpdateUser handler() {
        IUserRepository userRepository = UserJPARepository.getInstance();
        FindUserById findUserById = new FindUserById(userRepository);

        return new UpdateUser(userRepository, findUserById);
    }
}
