package br.upe.sisepei.core.user.service.factories;

import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.service.ListUsers;

public class ListUsersJPA {

    public ListUsers handle() {
        IUserRepository userRepository = UserJPARepository.getInstance();
        return new ListUsers(userRepository);
    }
}
