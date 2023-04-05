package br.upe.sisepei.sisepei.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.core.usuarioPerfil.UsuarioPerfilServico;

@RestController
@RequestMapping("usuario/perfil")
public class UsuarioPerfilApi {
	
	@Autowired
	private UsuarioPerfilServico usuarioPerfilServico;
	
	@PutMapping("{usuarioId}/{perfilNome}")
	public ResponseEntity<?> adicionarPerfil(@PathVariable Long usuarioId, @PathVariable String perfilNome) {
		try {
			usuarioPerfilServico.adicionarPerfil(perfilNome, usuarioId);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{usuarioId}/{perfilNome}")
	public ResponseEntity<?> removerPerfil(@PathVariable Long usuarioId, @PathVariable String perfilNome) {
		try {
			usuarioPerfilServico.removerPerfil(perfilNome, usuarioId);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}

}
