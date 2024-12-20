package br.upe.sisepei.core.profile;

import java.util.List;
import java.util.Optional;

import br.upe.sisepei.core.profile.model.ProfileEnum;

import br.upe.sisepei.core.profile.model.Profile;

public interface IProfileRepository {
	
	Optional<Profile> findByName(ProfileEnum name);
	List<Profile> findAll();
	List<Profile> findByIds(List<Long> ids);
}
