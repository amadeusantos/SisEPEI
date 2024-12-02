package br.upe.sisepei.core.user.useCases;

import br.upe.sisepei.core.profile.IProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateUserProfilesUseCaseTest {

    @InjectMocks
    private UpdateUserProfilesUseCase updateUserProfilesUseCase;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IProfileRepository profileRepository;

    private User user;

    private List<Profile> profiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        profiles = List.of(new Profile(), new Profile());
    }

    @Test
    void execute() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(profileRepository.findByIds(any())).thenReturn(profiles);

        updateUserProfilesUseCase.execute(1L, List.of(1L,2L,3L));

        assertEquals(user.getProfiles(), profiles);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void execute_NotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> updateUserProfilesUseCase.execute(1L, List.of(1L)));
    }
}