package br.upe.sisepei;

import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.core.user.UserRepository;
import br.upe.sisepei.core.user.UserService;
import br.upe.sisepei.core.user.model.LoginDTO;
import br.upe.sisepei.core.user.model.RegisterDTO;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;
import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import br.upe.sisepei.utils.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    private User user;
    private final String testEmail = "test@example.com";
    private final String testPassword = "password";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail(testEmail);
        user.setPassword(testPassword);
    }

    @Test
    void testListUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.listUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindUserById_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1L);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.findUserById(1L);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testRegister_UserSuccessfullyRegistered() {
        RegisterDTO request = new RegisterDTO("Test User", testEmail, testPassword);
        when(userRepository.existsByEmail(testEmail)).thenReturn(false);
        when(passwordEncoder.encode(testPassword)).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwtToken");

        AuthenticationResponse response = userService.register(request);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        verify(userRepository, times(1)).existsByEmail(testEmail);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegister_EmailAlreadyRegistered() {
        RegisterDTO request = new RegisterDTO("Test User", testEmail, testPassword);
        when(userRepository.existsByEmail(testEmail)).thenReturn(true);

        UnprocessableEntityException exception = assertThrows(UnprocessableEntityException.class, () -> {
            userService.register(request);
        });

        assertEquals("Email already registered", exception.getMessage());
    }

    @Test
    void testAuthenticate_UserSuccessfullyAuthenticated() {
        LoginDTO request = new LoginDTO(testEmail, testPassword);
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(testEmail)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        AuthenticationResponse response = userService.authenticate(request);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        verify(userRepository, times(1)).findByEmail(testEmail);
    }

    @Test
    void testUpdateUser_UserExists() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Updated User");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(1L, userDTO);

        assertNotNull(updatedUser);
        assertEquals("Updated User", updatedUser.getName());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser_UserNotFound() {
        UserDTO userDTO = new UserDTO();
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.updateUser(1L, userDTO);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testDeleteUser_UserExists() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("User not found", exception.getMessage());
    }
}
