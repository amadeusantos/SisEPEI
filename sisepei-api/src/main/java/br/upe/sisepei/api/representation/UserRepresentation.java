package br.upe.sisepei.api.representation;

import java.util.List;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.model.User;
import lombok.Getter;

@Getter
public class UserRepresentation {

	private final Long id;

	private final String name;

	private final String email;

	private final List<ProfileRepresentation> profiles;

	public UserRepresentation(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.profiles = user.getProfiles().stream().map(ProfileRepresentation::new).toList();
	}
}

