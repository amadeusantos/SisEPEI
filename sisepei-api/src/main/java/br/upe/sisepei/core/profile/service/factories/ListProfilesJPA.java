package br.upe.sisepei.core.profile.service.factories;

import br.upe.sisepei.core.profile.respository.ProfileJPARepository;
import br.upe.sisepei.core.profile.respository.interfaces.ProfileRepository;
import br.upe.sisepei.core.profile.service.ListProfiles;

public class ListProfilesJPA {

    public ListProfiles handle() {
        ProfileRepository profileRepository = ProfileJPARepository.getInstance();
        return new ListProfiles(profileRepository);
    }
}
