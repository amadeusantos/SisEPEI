package br.upe.sisepei.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.sisepei.api.representation.UsuarioRepresentation;
import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.base.exception.ValidacaoException;
import br.upe.sisepei.sisepei.core.usuario.UsuarioServico;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.sisepei.core.usuario.modelo.UsuarioDTO;


@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {

	@Autowired
	private UsuarioServico usuarioServico;


	@GetMapping
	public ResponseEntity<List<UsuarioRepresentation>> listarUsuarios() {
		return ResponseEntity.ok(usuarioServico.listarUsuarios()
				.stream().map(this::converter).collect(Collectors.toList()));
	}

	@GetMapping("/perfil")
	public ResponseEntity<?> buscarUsuarioPerfil(
			@RequestHeader(name = "Authorization", required = true) String token
	) {
		try {
			String jwt = token.substring(7);
			return ResponseEntity.ok(converter(usuarioServico.buscarUsuarioPerfil(jwt)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarUsuario(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(converter(usuarioServico.buscarUsuario(id)));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) throws ValidacaoException {
		try {
			return ResponseEntity.ok(converter(usuarioServico.alterarUsuario(id, usuarioDTO)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
		try { 
			usuarioServico.excluirUsuario(id);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	private UsuarioRepresentation converter(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioRepresentation.class);
	}
	
}
