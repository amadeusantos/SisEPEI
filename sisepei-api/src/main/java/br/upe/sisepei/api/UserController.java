package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.utils.exceptions.UnprocessableEntityException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import br.upe.sisepei.core.user.useCases.DeleteUserUseCase;
import br.upe.sisepei.core.user.useCases.FindUserByIdUseCase;
import br.upe.sisepei.core.user.useCases.ListUsersUseCase;
import br.upe.sisepei.core.user.useCases.UpdateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.api.representation.UserRepresentation;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final ListUsersUseCase listUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping
    public ResponseEntity<List<UserRepresentation>> listUser() {
        return ResponseEntity.ok(listUsersUseCase.execute()
                .stream().map(UserRepresentation::new).collect(Collectors.toList()));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> findUserProfiles(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(new UserRepresentation(findUserByIdUseCase.execute(user.getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserRepresentation(findUserByIdUseCase.execute(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id, 
            @Valid @RequestBody UserDTO userDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasFieldErrors()) {
            throw new UnprocessableEntityException("Error when edit user", bindingResult.getFieldErrors());
        }
        return ResponseEntity.ok(new UserRepresentation(updateUserUseCase.execute(id, userDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
