package br.upe.sisepei.core.profile.respository;

import java.util.Optional;

import br.upe.sisepei.core.profile.model.ProfileEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.core.profile.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	Optional<Profile> findByName(ProfileEnum name);

}
