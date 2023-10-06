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
		try {
			return ResponseEntity.ok(profileService.ListUsersByProfile(profileEnum).stream()
					.map(this::convertToUser).collect(Collectors.toList()));
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/users/{profileEnum}/{userId}")
	public ResponseEntity<?> AddProfileToUser(@PathVariable ProfileEnum profileEnum, @PathVariable Long userId) {
		try {
			profileService.addProfileToUser(profileEnum, userId);
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/users/{profile}/{userId}")
	public ResponseEntity<?> removeProfileToUser(@PathVariable ProfileEnum profile, @PathVariable Long userId) {
		try {
			profileService.removeProfileToUser(profile, userId);
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

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
