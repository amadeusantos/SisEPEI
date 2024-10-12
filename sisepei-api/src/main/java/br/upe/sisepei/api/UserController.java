package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.core.user.service.*;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.api.representation.UserRepresentation;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final ListUsers listUsers;
	private final FindUsersProfiles findUsersProfiles;
	private final FindUserById findUserById;
	private final UpdateUser updateUser;
	private final DeleteUser deleteUser;

	@GetMapping
	public ResponseEntity<List<UserRepresentation>> listUser() {
		return ResponseEntity.ok(
				listUsers
						.execute()
						.stream()
						.map(this::convertToUserRepresentation)
						.collect(Collectors.toList()));
	}

	@GetMapping("/profile")
	public ResponseEntity<?> findUserProfiles(
			@RequestHeader(name = "Authorization") String token
	) {
		try {
			String jwt = token.substring(7);
			return ResponseEntity.ok(convertToUserRepresentation(findUsersProfiles.execute(jwt)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(convertToUserRepresentation(findUserById.execute(id)));
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
		try {
			return ResponseEntity.ok(convertToUserRepresentation(updateUser.execute(id, userDTO)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try { 
			deleteUser.execute(id);
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}

	// TODO Remover
	private UserRepresentation convertToUserRepresentation(User user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, UserRepresentation.class);
	}
	
}
