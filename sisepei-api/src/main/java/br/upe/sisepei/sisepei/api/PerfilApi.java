package br.upe.sisepei.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.sisepei.core.perfil.modelo.PerfilEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.sisepei.api.representation.PerfilRepresentation;
import br.upe.sisepei.sisepei.api.representation.UsuarioRepresentation;
import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.core.perfil.PerfilServico;
import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;

@RestController
@RequestMapping("/perfis")
public class PerfilApi {
	
	@Autowired
	private PerfilServico perfilServico;

	@GetMapping
	public List<PerfilRepresentation> listarPerfis() {
		return perfilServico.ListarPerfis().stream().map(this::converter).collect(Collectors.toList());
	}

	@GetMapping("/{perfil}")
	public ResponseEntity<?> ListarUsuariosDoPerfil(@PathVariable PerfilEnum perfil) {
		try {
			return ResponseEntity.ok(perfilServico.ListarUsuariosDoPerfil(perfil).stream()
					.map(this::converterUsuarios).collect(Collectors.toList()));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/usuarios/{perfil}/{usuarioId}")
	public ResponseEntity<?> adicionarPerfilUsuario(@PathVariable PerfilEnum perfil, @PathVariable Long usuarioId) {
		try {
			perfilServico.adicionarPerfilUsuario(perfil, usuarioId);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/usuarios/{perfil}/{usuarioId}")
	public ResponseEntity<?> removerPerfilUsuario(@PathVariable PerfilEnum perfil, @PathVariable Long usuarioId) {
		try {
			perfilServico.removerPerfilUsuario(perfil, usuarioId);
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.noContent().build();
	}
	
	private PerfilRepresentation converter(Perfil perfil) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(perfil, PerfilRepresentation.class);
	}

	private UsuarioRepresentation converterUsuarios(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioRepresentation.class);
	}
	
}
