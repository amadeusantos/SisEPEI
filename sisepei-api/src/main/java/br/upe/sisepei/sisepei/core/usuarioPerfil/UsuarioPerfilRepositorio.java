package br.upe.sisepei.sisepei.core.usuarioPerfil;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.sisepei.core.usuarioPerfil.modelo.UsuarioPerfil;

public interface UsuarioPerfilRepositorio extends JpaRepository<UsuarioPerfil, Long> {

	Optional<UsuarioPerfil> findByUsuarioAndPerfil(Usuario usuario, Perfil perfil);
	
}
