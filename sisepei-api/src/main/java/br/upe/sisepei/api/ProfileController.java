package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.utils.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.api.representation.ProfileRepresentation;
import br.upe.sisepei.api.representation.UsuarioRepresentation;
import br.upe.sisepei.core.profile.ProfileService;
import br.upe.sisepei.core.usuario.modelo.Usuario;

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
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/users/{profileEnum}/{userId}")
	public ResponseEntity<?> AddProfileToUser(@PathVariable ProfileEnum profileEnum, @PathVariable Long userId) {
		try {
			profileService.addProfileToUser(profileEnum, userId);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/users/{profile}/{userId}")
	public ResponseEntity<?> removeProfileToUser(@PathVariable ProfileEnum profile, @PathVariable Long userId) {
		try {
			profileService.removeProfileToUser(profile, userId);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.noContent().build();
	}
	
	private ProfileRepresentation convertToProfile(Profile profile) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(profile, ProfileRepresentation.class);
	}

	private UsuarioRepresentation convertToUser(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioRepresentation.class);
	}
	
}
