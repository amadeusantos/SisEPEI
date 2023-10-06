package br.upe.sisepei.core.profile;

import java.util.List;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.usuario.UsuarioRepositorio;
import br.upe.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.utils.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

	private final ProfileRepository profileRepository;

	private final UsuarioRepositorio usuarioRepositorio;
	
	public List<Profile> listProfile() {
		return profileRepository.findAll();
	}

	public List<Usuario> ListUsersByProfile(ProfileEnum profileEnum) throws NaoEncontradoException {
		return profileRepository.findByName(profileEnum)
				.orElseThrow(() -> new NaoEncontradoException("Profile not found")).getUsers();
	}

	public void addProfileToUser(ProfileEnum profileEnum, Long userId) throws NaoEncontradoException {
		Profile profile = profileRepository
				.findByName(profileEnum).orElseThrow(() -> new NaoEncontradoException("Profile not found"));

		Usuario user = usuarioRepositorio.findById(userId)
				.orElseThrow(() -> new NaoEncontradoException("User not found"));

		if (user.getProfiles().contains(profile)) {
			throw new NaoEncontradoException("User already has this profile");
		}

		user.getProfiles().add(profile);

		usuarioRepositorio.save(user);
	}

	public void removeProfileToUser(ProfileEnum profileEnum, Long userId) throws NaoEncontradoException {
		Profile profile = profileRepository
				.findByName(profileEnum).orElseThrow(() -> new NaoEncontradoException("Profile not found"));

		Usuario user = usuarioRepositorio.findById(userId)
				.orElseThrow(() -> new NaoEncontradoException("User not found"));

		if (!user.getProfiles().contains(profile)) {
			throw new NaoEncontradoException("The user does not have this profile");
		}

		user.getProfiles().remove(profile);

		usuarioRepositorio.save(user);
	}

}
