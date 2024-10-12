package br.upe.sisepei.core.user.repository.interfaces;

import java.util.List;
import java.util.Optional;

import br.upe.sisepei.core.user.model.User;

public interface IUserRepository {

	boolean existsByEmail(String email);
	boolean existsById(Long id);
	Optional<User> findByEmail(String email);
	Optional<User> findById(Long id);
	List<User> findAll();
	void deleteById(Long id);
	User save(User user);
}
