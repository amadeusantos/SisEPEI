package br.upe.sisepei.sisepei.core.perfil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;

@Service
public class PerfilServico {

	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	public List<Perfil> ListarPerfis() {
		return perfilRepositorio.findAll();
	}
	
	public Perfil ListarUsuariosDoPerfil(String nome) throws NaoEncontradoException {
		Optional<Perfil> perfilExistente = perfilRepositorio.findByNome(nome);
		
		if (perfilExistente.isEmpty()) {
			throw new NaoEncontradoException("Perfil não encontrado!");
		}
		
		return perfilExistente.get();
	}
}
