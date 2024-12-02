package br.upe.sisepei.core.user.useCases;

import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListUsersUseCaseTest {

    @InjectMocks
    private ListUsersUseCase listUsersUseCase;

    @Mock
    private IUserRepository userRepository;

    private List<User> users;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        users = new ArrayList<>();
        User user = new User();
        User user2 = new User();
        users.add(user);
        users.add(user2);
    }

    @Test
    void execute() {
        when(userRepository.findAll()).thenReturn(users);

        var result = listUsersUseCase.execute();

        assertNotNull(result);
        assertEquals(users, result);
    }
}