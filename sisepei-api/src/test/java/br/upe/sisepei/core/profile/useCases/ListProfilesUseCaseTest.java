package br.upe.sisepei.core.profile.useCases;

import br.upe.sisepei.core.profile.IProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListProfilesUseCaseTest {

    @InjectMocks
    private ListProfilesUseCase listProfilesUseCase;

    @Mock
    private IProfileRepository profileRepository;

    private List<Profile> profiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        profiles = new ArrayList<>();
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        profiles.add(profile1);
        profiles.add(profile2);
    }

    @Test
    void execute() {
        when(profileRepository.findAll()).thenReturn(profiles);

        var result = listProfilesUseCase.execute();

        assertNotNull(result);
        assertEquals(profiles, result);
    }
}