package br.upe.sisepei;

import br.upe.sisepei.core.profile.ProfileRepository;
import br.upe.sisepei.core.profile.ProfileService;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.UserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    private Profile profile;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        profile = new Profile();
        profile.setId(1L);
        profile.setName(ProfileEnum.ADMINISTRADOR);
        profile.setUsers(new ArrayList<>());

        user = new User();
        user.setId(1L);
        user.setProfiles(new ArrayList<>());
    }

    @Test
    void testListProfile() {
        when(profileRepository.findAll()).thenReturn(Arrays.asList(profile));

        List<Profile> profiles = profileService.listProfile();

        assertNotNull(profiles);
        assertEquals(1, profiles.size());
        assertEquals(profile, profiles.get(0));
        verify(profileRepository, times(1)).findAll();
    }

    @Test
    void testListUsersByProfile_ProfileExists() {
        profile.setUsers(Arrays.asList(user));
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.of(profile));

        List<User> users = profileService.ListUsersByProfile(ProfileEnum.ADMINISTRADOR);

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
        verify(profileRepository, times(1)).findByName(ProfileEnum.ADMINISTRADOR);
    }

    @Test
    void testListUsersByProfile_ProfileNotFound() {
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profileService.ListUsersByProfile(ProfileEnum.ADMINISTRADOR);
        });

        assertEquals("Profile not found", exception.getMessage());
    }

    @Test
    void testAddProfileToUser_UserAndProfileExist() {
        user.setProfiles(new ArrayList<>());
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.of(profile));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        profileService.addProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);

        assertTrue(user.getProfiles().contains(profile));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testAddProfileToUser_UserNotFound() {
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.of(profile));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profileService.addProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testAddProfileToUser_ProfileNotFound() {
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profileService.addProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);
        });

        assertEquals("Profile not found", exception.getMessage());
    }

    @Test
    void testAddProfileToUser_UserAlreadyHasProfile() {
        user.getProfiles().add(profile);
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.of(profile));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profileService.addProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);
        });

        assertEquals("User already has this profile", exception.getMessage());
    }

    @Test
    void testRemoveProfileToUser_UserAndProfileExist() {
        user.getProfiles().add(profile);
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.of(profile));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        profileService.removeProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);

        assertFalse(user.getProfiles().contains(profile));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRemoveProfileToUser_UserNotFound() {
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.of(profile));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profileService.removeProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testRemoveProfileToUser_ProfileNotFound() {
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profileService.removeProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);
        });

        assertEquals("Profile not found", exception.getMessage());
    }

    @Test
    void testRemoveProfileToUser_UserDoesNotHaveProfile() {
        when(profileRepository.findByName(ProfileEnum.ADMINISTRADOR)).thenReturn(Optional.of(profile));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profileService.removeProfileToUser(ProfileEnum.ADMINISTRADOR, 1L);
        });

        assertEquals("The user does not have this profile", exception.getMessage());
    }
}
