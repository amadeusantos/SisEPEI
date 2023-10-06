package br.upe.sisepei.core.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.core.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
	
}
