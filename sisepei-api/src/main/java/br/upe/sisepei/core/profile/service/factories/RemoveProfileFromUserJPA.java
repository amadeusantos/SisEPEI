package br.upe.sisepei.core.profile.service.factories;

import br.upe.sisepei.core.profile.respository.ProfileJPARepository;
import br.upe.sisepei.core.profile.respository.interfaces.ProfileRepository;
import br.upe.sisepei.core.profile.service.RemoveProfileFromUser;
import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;

public class RemoveProfileFromUserJPA {

    public RemoveProfileFromUser handle() {
        ProfileRepository profileRepository = ProfileJPARepository.getInstance();
        IUserRepository userRepository = UserJPARepository.getInstance();

        return new RemoveProfileFromUser(profileRepository, userRepository);
    }
}
