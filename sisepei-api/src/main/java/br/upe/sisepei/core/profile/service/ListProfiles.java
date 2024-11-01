package br.upe.sisepei.core.profile.service;

import br.upe.sisepei.core.profile.respository.ProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListProfiles {
    private final ProfileRepository profileRepository;

    public List<Profile> execute() {
        return profileRepository.findAll();
    }
}
