package br.upe.sisepei.core.profile.useCases;

import br.upe.sisepei.core.profile.IProfileRepository;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsersByProfileUseCase {

    private final IProfileRepository profileRepository;

    public List<User> execute(ProfileEnum profileEnum) {
        return profileRepository.findByName(profileEnum)
                .orElseThrow(() -> new NotFoundException("Profile not found")).getUsers();
    }
}
