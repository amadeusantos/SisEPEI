package br.upe.sisepei.core.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.core.user.model.User;

public interface IUserRepository {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	void deleteById(Long id);

	Optional<User> findById(Long id);

	List<User> findAll();

	User save(User user);

	boolean existsById(Long id);
}
