package br.upe.sisepei.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.sisepei.sisepei.api.representation.UsuarioRepresentation;
import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.base.exception.ValidacaoException;
import br.upe.sisepei.sisepei.core.usuario.UsuarioServico;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.sisepei.core.usuario.modelo.UsuarioDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioApi {

	@Autowired
	private UsuarioServico usuarioServico;
	
	@GetMapping
	public ResponseEntity<List<UsuarioRepresentation>> listarUsuarios() {
		return ResponseEntity.ok(usuarioServico.listarUsuarios()
				.stream().map(this::converter).collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarUsuario(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(converter(usuarioServico.buscarUsuario(id)));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> incluirUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(converter(usuarioServico.incluirUsuario(usuarioDTO)));
		} catch (Exception e) {
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
