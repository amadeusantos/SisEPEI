package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.profile.service.AddProfileToUser;
import br.upe.sisepei.core.profile.service.ListProfiles;
import br.upe.sisepei.core.profile.service.ListUsersByProfile;
import br.upe.sisepei.core.profile.service.RemoveProfileFromUser;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.api.representation.ProfileRepresentation;
import br.upe.sisepei.api.representation.UserRepresentation;
import br.upe.sisepei.core.user.model.User;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

	private final ListProfiles listProfiles;
	private final ListUsersByProfile listUsersByProfile;
	private final AddProfileToUser addProfileToUser;
	private final RemoveProfileFromUser removeProfileFromUser;

	@GetMapping
	public List<ProfileRepresentation> ListProfiles() {
		return listProfiles
				.execute()
				.stream()
				.map(this::convertToProfile)
				.collect(Collectors.toList());
	}

	@GetMapping("/{profileEnum}")
	public ResponseEntity<?> ListUsersByProfile(@PathVariable ProfileEnum profileEnum) {
			return ResponseEntity.ok(
					listUsersByProfile
							.execute(profileEnum)
							.stream()
							.map(this::convertToUser)
							.collect(Collectors.toList()));
	}

	@PutMapping("/users/{profileEnum}/{userId}")
	public ResponseEntity<?> AddProfileToUser(@PathVariable ProfileEnum profileEnum, @PathVariable Long userId) {
		try {
			addProfileToUser.execute(profileEnum, userId);
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/users/{profile}/{userId}")
	public ResponseEntity<?> removeProfileToUser(@PathVariable ProfileEnum profile, @PathVariable Long userId) {
			removeProfileFromUser.execute(profile, userId);

		return ResponseEntity.noContent().build();
	}

	// TODO Remover
	private ProfileRepresentation convertToProfile(Profile profile) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(profile, ProfileRepresentation.class);
	}

	// TODO Remover
	private UserRepresentation convertToUser(User user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, UserRepresentation.class);
	}
	
}
