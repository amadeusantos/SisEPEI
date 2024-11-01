package br.upe.sisepei.core.profile.respository.interfaces;

import java.util.List;
import java.util.Optional;

import br.upe.sisepei.core.profile.model.ProfileEnum;

import br.upe.sisepei.core.profile.model.Profile;

public interface ProfileRepository{
	
	Optional<Profile> findByName(ProfileEnum name);
	List<Profile> findAll();
}
