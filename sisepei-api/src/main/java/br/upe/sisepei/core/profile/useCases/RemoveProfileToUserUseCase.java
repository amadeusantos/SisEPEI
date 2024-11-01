package br.upe.sisepei.core.profile.useCases;

import br.upe.sisepei.core.profile.IProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveProfileToUserUseCase {

    private final IProfileRepository profileRepository;

    private final IUserRepository userRepository;

    public void execute(ProfileEnum profileEnum, Long userId) {
        Profile profile = profileRepository
                .findByName(profileEnum).orElseThrow(() -> new NotFoundException("Profile not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.getProfiles().contains(profile)) {
            throw new NotFoundException("The user does not have this profile");
        }

        user.getProfiles().remove(profile);

        userRepository.save(user);
    }
}
