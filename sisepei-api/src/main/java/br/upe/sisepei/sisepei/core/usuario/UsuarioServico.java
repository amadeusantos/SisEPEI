package br.upe.sisepei.sisepei.core.usuario;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.auth.AuthenticationRequest;
import br.upe.sisepei.sisepei.auth.AuthenticationResponse;
import br.upe.sisepei.sisepei.auth.RegisterRequestDTO;
import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.base.exception.ValidacaoException;
import br.upe.sisepei.sisepei.config.JwtService;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.sisepei.core.usuario.modelo.UsuarioDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServico {

	@Autowired
	private final UsuarioRepositorio repository;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final JwtService jwtService;
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	public List<Usuario> listarUsuarios() {
		return repository.findAll();
	}

	public Usuario buscarUsuarioPerfil(String token) throws NaoEncontradoException {
		String email = jwtService.extractUserEmail(token);
		Optional<Usuario> usuario = repository.findByEmail(email);
		if (usuario.isEmpty()) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}

		return usuario.get();
	}
	
	public Usuario buscarUsuario(Long id) throws NaoEncontradoException {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isEmpty()) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}
		
		return usuario.get();
	}
	
    
    public AuthenticationResponse register(RegisterRequestDTO request) throws ValidacaoException{
        if (repository.existsByEmail(request.getEmail())) {
			throw new ValidacaoException("Email já cadastrado por outro usuário!");
		}
            var user = Usuario.builder()
               .nome(request.getNome())
               .email(request.getEmail())
               .senha(passwordEncoder.encode(request.getSenha()))
               .build();
           repository.save(user);
           var jwtToken = jwtService.generateToken(user);
           return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
        
    }

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));
		var user = repository.findByEmail(request.getEmail())
			.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
			.token(jwtToken)
			.build();
}
	
	public Usuario alterarUsuario(Long id, UsuarioDTO usuarioDTO) throws NaoEncontradoException, ValidacaoException {
		Optional<Usuario> usuarioExistente = repository.findById(id);
		
		if (usuarioExistente.isEmpty()) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}
		
		if (!(usuarioExistente.get().getEmail().equals(usuarioDTO.getEmail()))
				&& repository.existsByEmail(usuarioDTO.getEmail())) {
			throw new ValidacaoException("Email já cadastrado por outro usuário!");
		}
		
		Usuario usuario = converterDTO(usuarioDTO);
		usuario.setId(id);
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		
		usuario.setPerfis(usuarioExistente.get().getPerfis());
		usuario.setEditais(usuarioExistente.get().getEditais());
		
		return repository.save(usuario);
	}
	
	public void excluirUsuario(Long id) throws NaoEncontradoException {
		if (!repository.existsById(id)) {
			throw new NaoEncontradoException("Usuário não encontrado!");
		}

		repository.deleteById(id);
	}
	
	
	private Usuario converterDTO(UsuarioDTO usuarioDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuarioDTO, Usuario.class);
	}
	
}
