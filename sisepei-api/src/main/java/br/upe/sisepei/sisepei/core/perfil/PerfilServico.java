package br.upe.sisepei.sisepei.core.perfil;

import java.util.List;
import java.util.Optional;

import br.upe.sisepei.sisepei.core.perfil.modelo.PerfilEnum;
import br.upe.sisepei.sisepei.core.usuario.UsuarioRepositorio;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;

@Service
public class PerfilServico {

	@Autowired
	private PerfilRepositorio perfilRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public List<Perfil> ListarPerfis() {
		return perfilRepositorio.findAll();
	}

	public List<Usuario> ListarUsuariosDoPerfil(PerfilEnum nome) throws NaoEncontradoException {
		Optional<Perfil> perfilExistente = perfilRepositorio.findByNome(nome);

		if (perfilExistente.isEmpty()) {
			throw new NaoEncontradoException("Perfil não encontrado!");
		}

		return perfilExistente.get().getUsuarios();
	}

	public void adicionarPerfilUsuario(PerfilEnum perfil, Long usuarioId) throws NaoEncontradoException {
		Optional<Perfil> perfilExistente = perfilRepositorio.findByNome(perfil);

		if (perfilExistente.isEmpty()) {
			throw new NaoEncontradoException("Perfil não encontrado!");
		}

		Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioId);

		if (usuario.isEmpty()) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}

		if (usuario.get().getPerfis().contains(perfilExistente.get())) {
			throw new NaoEncontradoException("Usuário já possui esse perfil!");
		}

		usuario.get().getPerfis().add(perfilExistente.get());

		usuarioRepositorio.save(usuario.get());
	}

	public void removerPerfilUsuario(PerfilEnum perfil, Long usuarioId) throws NaoEncontradoException {
		Optional<Perfil> perfilExistente = perfilRepositorio.findByNome(perfil);

		if (perfilExistente.isEmpty()) {
			throw new NaoEncontradoException("Perfil não encontrado!");
		}

		Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioId);

		if (usuario.isEmpty()) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}

		if (!usuario.get().getPerfis().contains(perfilExistente.get())) {
			throw new NaoEncontradoException("Usuário já não possui esse perfil!");
		}

		usuario.get().getPerfis().remove(perfilExistente.get());

		usuarioRepositorio.save(usuario.get());
	}

}
