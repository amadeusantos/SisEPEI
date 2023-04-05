package br.upe.sisepei.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> ListarUsuariosDoPerfil(@PathVariable String perfil) {
		try {
			return ResponseEntity.ok(perfilServico.ListarUsuariosDoPerfil(perfil).getUsuarios().stream()
					.map(usuarioPerfil -> converterUsuarios(usuarioPerfil.getUsuario())).collect(Collectors.toList()));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
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
