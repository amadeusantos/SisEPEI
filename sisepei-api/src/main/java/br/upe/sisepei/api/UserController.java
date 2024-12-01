package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.core.user.model.UpdateUserProfilesDTO;
import br.upe.sisepei.core.user.useCases.*;
import br.upe.sisepei.utils.exceptions.UnprocessableEntityException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
    private final UpdateUserProfilesUseCase updateUserProfilesUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping
    public ResponseEntity<List<UserRepresentation>> listUser() {
        return ResponseEntity.ok(listUsersUseCase.execute()
                .stream().map(UserRepresentation::new).collect(Collectors.toList()));
    }

    @GetMapping("/me")
    public ResponseEntity<?> findUserProfiles(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(new UserRepresentation(findUserByIdUseCase.execute(user.getId())));
    }

    @PatchMapping("{id}/profiles")
    public ResponseEntity<Void> updateUserProfiles(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserProfilesDTO updateUserProfilesDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasFieldErrors()) {
            throw new UnprocessableEntityException("Error when update user profiles", bindingResult.getFieldErrors());
        }
        updateUserProfilesUseCase.execute(id, updateUserProfilesDTO.getProfileIds());
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
