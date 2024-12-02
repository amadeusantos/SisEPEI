package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.profile.useCases.AddProfileToUserUseCase;
import br.upe.sisepei.core.profile.useCases.ListProfilesUseCase;
import br.upe.sisepei.core.profile.useCases.ListUsersByProfileUseCase;
import br.upe.sisepei.core.profile.useCases.RemoveProfileToUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.api.representation.ProfileRepresentation;
import br.upe.sisepei.api.representation.UserRepresentation;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

	private final ListProfilesUseCase listProfilesUseCase;
	private final ListUsersByProfileUseCase listUsersByProfileUseCase;
	private final AddProfileToUserUseCase addProfileToUserUseCase;
	private final RemoveProfileToUserUseCase removeProfileToUserUseCase;

	@GetMapping
	public List<ProfileRepresentation> listProfiles() {
		return listProfilesUseCase.execute().stream().map(ProfileRepresentation::new).toList();
	}

	@GetMapping("/{profileEnum}")
	public ResponseEntity<?> ListUsersByProfile(@PathVariable ProfileEnum profileEnum) {
		return ResponseEntity.ok(listUsersByProfileUseCase.execute(profileEnum).stream()
				.map(UserRepresentation::new).collect(Collectors.toList()));
	}

	@PutMapping("/users/{profileEnum}/{userId}")
	public ResponseEntity<?> AddProfileToUser(@PathVariable ProfileEnum profileEnum, @PathVariable Long userId) {
		addProfileToUserUseCase.execute(profileEnum, userId);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/users/{profile}/{userId}")
	public ResponseEntity<?> removeProfileToUser(@PathVariable ProfileEnum profile, @PathVariable Long userId) {
		removeProfileToUserUseCase.execute(profile, userId);
		return ResponseEntity.noContent().build();
	}
}
