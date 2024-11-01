package br.upe.sisepei.core.profile.service.factories;

import br.upe.sisepei.core.profile.respository.ProfileJPARepository;
import br.upe.sisepei.core.profile.respository.interfaces.ProfileRepository;
import br.upe.sisepei.core.profile.service.ListProfiles;
import br.upe.sisepei.core.profile.service.ListUsersByProfile;

public class ListUsersByProfileJPA {

    public ListUsersByProfile handle() {
        ProfileRepository profileRepository = ProfileJPARepository.getInstance();

        return new ListUsersByProfile(profileRepository);
    }
}
