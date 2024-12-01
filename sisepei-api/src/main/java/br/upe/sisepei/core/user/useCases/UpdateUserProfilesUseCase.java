package br.upe.sisepei.core.user.useCases;

import br.upe.sisepei.core.profile.IProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateUserProfilesUseCase {

    private final IUserRepository userRepository;
    
    private final IProfileRepository profileRepository;

    public void execute(Long userId, List<Long> profileIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<Profile> profiles = profileRepository.findByIds(profileIds);
        user.setProfiles(profiles);
        userRepository.save(user);
    }
}
