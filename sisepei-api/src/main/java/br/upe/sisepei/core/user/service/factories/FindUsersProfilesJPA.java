package br.upe.sisepei.core.user.service.factories;

import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.service.FindUsersProfiles;

public class FindUsersProfilesJPA {

    public FindUsersProfiles handle() {
        IUserRepository userRepository = UserJPARepository.getInstance();
        JwtService jwtService = new JwtService();
        return new FindUsersProfiles(userRepository, jwtService);
    }
}
