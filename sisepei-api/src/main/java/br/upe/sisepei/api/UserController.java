package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.utils.exceptions.NaoEncontradoException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.api.representation.UserRepresentation;
import br.upe.sisepei.core.user.UserService;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<UserRepresentation>> listUser() {
		return ResponseEntity.ok(userService.listUsers()
				.stream().map(this::convertToUserRepresentation).collect(Collectors.toList()));
	}

	@GetMapping("/profile")
	public ResponseEntity<?> findUserProfiles(
			@RequestHeader(name = "Authorization") String token
	) {
		try {
			String jwt = token.substring(7);
			return ResponseEntity.ok(convertToUserRepresentation(userService.findUserProfiles(jwt)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(convertToUserRepresentation(userService.findUserById(id)));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
		try {
			return ResponseEntity.ok(convertToUserRepresentation(userService.updateUser(id, userDTO)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try { 
			userService.deleteUser(id);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	private UserRepresentation convertToUserRepresentation(User user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, UserRepresentation.class);
	}
	
}
