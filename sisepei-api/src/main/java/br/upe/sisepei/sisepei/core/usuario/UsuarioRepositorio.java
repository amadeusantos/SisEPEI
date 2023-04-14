package br.upe.sisepei.sisepei.core.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	public boolean existsByEmail(String email);

	Optional<Usuario> findByEmail(String email);
	
}
