package br.upe.sisepei.core.profile;

import java.util.List;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.UserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

	private final ProfileRepository profileRepository;

	private final UserRepository userRepository;
	
	public List<Profile> listProfile() {
		return profileRepository.findAll();
	}

	public List<User> ListUsersByProfile(ProfileEnum profileEnum) throws NaoEncontradoException {
		return profileRepository.findByName(profileEnum)
				.orElseThrow(() -> new NaoEncontradoException("Profile not found")).getUsers();
	}

	public void addProfileToUser(ProfileEnum profileEnum, Long userId) throws NaoEncontradoException {
		Profile profile = profileRepository
				.findByName(profileEnum).orElseThrow(() -> new NaoEncontradoException("Profile not found"));

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NaoEncontradoException("User not found"));

		if (user.getProfiles().contains(profile)) {
			throw new NaoEncontradoException("User already has this profile");
		}

		user.getProfiles().add(profile);

		userRepository.save(user);
	}

	public void removeProfileToUser(ProfileEnum profileEnum, Long userId) throws NaoEncontradoException {
		Profile profile = profileRepository
				.findByName(profileEnum).orElseThrow(() -> new NaoEncontradoException("Profile not found"));

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NaoEncontradoException("User not found"));

		if (!user.getProfiles().contains(profile)) {
			throw new NaoEncontradoException("The user does not have this profile");
		}

		user.getProfiles().remove(profile);

		userRepository.save(user);
	}

}
