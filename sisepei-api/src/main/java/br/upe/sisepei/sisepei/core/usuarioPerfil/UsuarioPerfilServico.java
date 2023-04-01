package br.upe.sisepei.sisepei.core.usuarioPerfil;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.core.perfil.PerfilRepositorio;
import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;
import br.upe.sisepei.sisepei.core.usuario.UsuarioRepositorio;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.sisepei.core.usuarioPerfil.modelo.UsuarioPerfil;

@Service
public class UsuarioPerfilServico {

	@Autowired
	private UsuarioPerfilRepositorio usuarioPerfilRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	public void adicionarPerfil(String perfilNome, Long usuarioId) throws NaoEncontradoException {
		Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioId);
		if (usuario.isEmpty()) {
			throw new NaoEncontradoException("Usuario não encontrado!");
		}
		
		Optional<Perfil> perfil = perfilRepositorio.findByNome(perfilNome);
		
		if (perfil.isEmpty()) {
			throw new NaoEncontradoException("Perfil inexistente!");
		}
		
		Optional<UsuarioPerfil> usuarioPerfilOptional = usuarioPerfilRepositorio
				.findByUsuarioAndPerfil(usuario.get(), perfil.get());
		
		if (usuarioPerfilOptional.isPresent()) {
			throw new NaoEncontradoException("Usuario já possui essa Perfil!");
		}
		
		UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
		usuarioPerfil.setPerfil(perfil.get());
		usuarioPerfil.setUsuario(usuario.get());
		
		usuarioPerfilRepositorio.save(usuarioPerfil);
	}
	
	public void removerPerfil(String perfilNome, Long usuarioId) throws NaoEncontradoException {
		Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioId);
		if (usuario.isEmpty()) {
			throw new NaoEncontradoException("Usuario não encontrado!");
		}
		
		Optional<Perfil> perfil = perfilRepositorio.findByNome(perfilNome);
		
		if (perfil.isEmpty()) {
			throw new NaoEncontradoException("Perfil inexistente!");
		}
	
		Optional<UsuarioPerfil> usuarioPerfilOptional = usuarioPerfilRepositorio
				.findByUsuarioAndPerfil(usuario.get(), perfil.get());
		
		if (usuarioPerfilOptional.isEmpty()) {
			throw new NaoEncontradoException("Usuario já não possui essa Perfil!");
		}
		
		usuarioPerfilRepositorio.deleteById(usuarioPerfilOptional.get().getId());
	}
	
}
