package br.upe.sisepei.core.profile.useCases;

import br.upe.sisepei.core.profile.IProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListProfilesUseCase {
    private final IProfileRepository profileRepository;

    public List<Profile> execute() {
        return profileRepository.findAll();
    }
}
