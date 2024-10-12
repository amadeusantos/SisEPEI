package br.upe.sisepei.core.profile.service;

import br.upe.sisepei.core.profile.respository.ProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.repository.UserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProfileToUser {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public void execute(ProfileEnum profileEnum, Long userId) {
        Profile profile = profileRepository
                .findByName(profileEnum).orElseThrow(() -> new NotFoundException("Profile not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getProfiles().contains(profile)) {
            throw new NotFoundException("User already has this profile");
        }

        user.getProfiles().add(profile);

        userRepository.save(user);
    }
}
