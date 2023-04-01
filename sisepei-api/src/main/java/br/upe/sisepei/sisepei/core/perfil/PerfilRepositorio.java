package br.upe.sisepei.sisepei.core.perfil;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;

public interface PerfilRepositorio extends JpaRepository<Perfil, Long> {
	
	Optional<Perfil> findByNome(String nome);

}
