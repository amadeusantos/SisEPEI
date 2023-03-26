package br.upe.sisepei.sisepei.core.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	public List<Usuario> listarUsuarios() {
		return repositorio.findAll();
	}
	
	public Usuario buscarUsuario(Long id) {
		Optional<Usuario> usuario = repositorio.findById(id);
		if (usuario.isEmpty()) {
//			throw new 
		}
		
		return usuario.get();
	}
	
	public Usuario incluirUsuario(UsuarioDTO usuarioDTO) {
		if (repositorio.existsByEmail(usuarioDTO.getEmail())) {
			
		}
		
		Usuario usuario = converterDTO(usuarioDTO);
		
		return repositorio.save(usuario);
	}
	
	public Usuario alterarUsuario(Long id, UsuarioDTO usuarioDTO) {
		Optional<Usuario> usuarioExistente = repositorio.findById(id);
		
		if (usuarioExistente.isEmpty()) {
			
		}
		
		if (!(usuarioExistente.get().getEmail().equals(usuarioDTO.getEmail()))
				&& repositorio.existsByEmail(usuarioDTO.getEmail())) {
			
		}
		
		Usuario usuario = converterDTO(usuarioDTO);
		
		usuario.setPerfis(usuarioExistente.get().getPerfis());
//		usuario.setEditais(usuarioExistente.get().getEditais());
		
		return repositorio.save(usuario);
	}
	
	public void excluirUsuario(Long id) {
		if (repositorio.existsById(id)) {
			
		}
		
		repositorio.deleteById(id);
	}
	
	
	private Usuario converterDTO(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		//TODO: AS- Criptografar senha
		usuario.setSenha(usuarioDTO.getSenha());
		
		List<PerfilEnum> perfis = new ArrayList<>();
		
		perfis.add(PerfilEnum.GERAL);
		usuario.setPerfis(perfis);
//		usuario.setEditais(new ArrayList<>());
		
		return usuario;
	}
	
}
