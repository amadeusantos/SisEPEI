package br.upe.sisepei.core.profile.service;

import br.upe.sisepei.core.profile.respository.ProfileJPARepository;
import br.upe.sisepei.core.profile.respository.interfaces.ProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.repository.UserJPARepository;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class AddProfileToUser {

    private final ProfileRepository profileRepository;
    private final IUserRepository IUserRepository;

    public void execute(ProfileEnum profileEnum, Long userId) {
        Profile profile = profileRepository
                .findByName(profileEnum).orElseThrow(() -> new NotFoundException("Profile not found"));

        User user = IUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getProfiles().contains(profile)) {
            throw new NotFoundException("User already has this profile");
        }

        user.getProfiles().add(profile);

        IUserRepository.save(user);
    }
}
