package br.upe.sisepei.core.user.repository;

import java.util.Optional;

import br.upe.sisepei.core.user.model.User;

public interface UserRepository {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
	
}
