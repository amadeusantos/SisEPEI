package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.api.representation.ProfileRepresentation;
import br.upe.sisepei.api.representation.UserRepresentation;
import br.upe.sisepei.core.profile.ProfileService;
import br.upe.sisepei.core.user.model.User;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

	private final ProfileService profileService;

	@GetMapping
	public List<ProfileRepresentation> ListProfiles() {
		return profileService.listProfile().stream().map(this::convertToProfile).collect(Collectors.toList());
	}

	@GetMapping("/{profileEnum}")
	public ResponseEntity<?> ListUsersByProfile(@PathVariable ProfileEnum profileEnum) {
		return ResponseEntity.ok(profileService.ListUsersByProfile(profileEnum).stream()
				.map(this::convertToUser).collect(Collectors.toList()));
	}

	@PutMapping("/users/{profileEnum}/{userId}")
	public ResponseEntity<?> AddProfileToUser(@PathVariable ProfileEnum profileEnum, @PathVariable Long userId) {
		profileService.addProfileToUser(profileEnum, userId);

		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/users/{profile}/{userId}")
	public ResponseEntity<?> removeProfileToUser(@PathVariable ProfileEnum profile, @PathVariable Long userId) {
		profileService.removeProfileToUser(profile, userId);

		return ResponseEntity.noContent().build();
	}
	
	private ProfileRepresentation convertToProfile(Profile profile) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(profile, ProfileRepresentation.class);
	}

	private UserRepresentation convertToUser(User user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, UserRepresentation.class);
	}
	
}
