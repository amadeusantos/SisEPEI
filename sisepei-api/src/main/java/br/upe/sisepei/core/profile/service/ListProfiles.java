package br.upe.sisepei.core.profile.service;

import br.upe.sisepei.core.profile.respository.ProfileJPARepository;
import br.upe.sisepei.core.profile.respository.interfaces.ProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
public class ListProfiles {
    private final ProfileRepository profileRepository;

    public List<Profile> execute() {
        return profileRepository.findAll();
    }
}
