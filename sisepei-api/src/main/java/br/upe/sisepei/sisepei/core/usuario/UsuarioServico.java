package br.upe.sisepei.sisepei.core.usuario;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.base.exception.ValidacaoException;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.sisepei.core.usuario.modelo.UsuarioDTO;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	private PasswordEncoder passwordEncoder;
	
//	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}
	
	public Usuario buscarUsuario(Long id) throws NaoEncontradoException {
		Optional<Usuario> usuario = usuarioRepositorio.findById(id);
		if (usuario.isEmpty()) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}
		
		return usuario.get();
	}
	
	public Usuario incluirUsuario(UsuarioDTO usuarioDTO) throws ValidacaoException {
		if (usuarioRepositorio.existsByEmail(usuarioDTO.getEmail())) {
			throw new ValidacaoException("Email já cadastrado por outro usuário!");
		}
		
		Usuario usuario = converterDTO(usuarioDTO);
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		
		
		return usuarioRepositorio.save(usuario);
	}
	
	public Usuario alterarUsuario(Long id, UsuarioDTO usuarioDTO) throws NaoEncontradoException, ValidacaoException {
		Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(id);
		
		if (usuarioExistente.isEmpty()) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}
		
		if (!(usuarioExistente.get().getEmail().equals(usuarioDTO.getEmail()))
				&& usuarioRepositorio.existsByEmail(usuarioDTO.getEmail())) {
			throw new ValidacaoException("Email já cadastrado por outro usuário!");
		}
		
		Usuario usuario = converterDTO(usuarioDTO);
		usuario.setId(id);
//		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		
		usuario.setPerfis(usuarioExistente.get().getPerfis());
//		usuario.setEditais(usuarioExistente.get().getEditais());
		
		return usuarioRepositorio.save(usuario);
	}
	
	public void excluirUsuario(Long id) throws NaoEncontradoException {
		if (!usuarioRepositorio.existsById(id)) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}
		
		usuarioRepositorio.deleteById(id);
	}
	
	
	private Usuario converterDTO(UsuarioDTO usuarioDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuarioDTO, Usuario.class);
	}
	
}
