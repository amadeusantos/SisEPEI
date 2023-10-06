package br.upe.sisepei.core.perfil;

import java.util.Optional;

import br.upe.sisepei.core.perfil.modelo.PerfilEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.core.perfil.modelo.Perfil;

public interface PerfilRepositorio extends JpaRepository<Perfil, Long> {
	
	Optional<Perfil> findByNome(PerfilEnum nome);

}
