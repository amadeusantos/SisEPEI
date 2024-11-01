package br.upe.sisepei.core.profile.service;

import br.upe.sisepei.core.profile.respository.ProfileRepository;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsersByProfile {

    private final ProfileRepository profileRepository;

    public List<User> execute(ProfileEnum profileEnum) {
        return profileRepository
                .findByName(profileEnum)
                .orElseThrow(() -> new NotFoundException("Profile not found"))
                .getUsers();
    }
}
