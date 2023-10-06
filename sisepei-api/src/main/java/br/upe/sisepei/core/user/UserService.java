package br.upe.sisepei.core.user;

import java.util.List;

import br.upe.sisepei.utils.exceptions.NaoEncontradoException;
import br.upe.sisepei.utils.exceptions.ValidacaoException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.upe.sisepei.auth.AuthenticationRequest;
import br.upe.sisepei.auth.AuthenticationResponse;
import br.upe.sisepei.auth.RegisterRequestDTO;
import br.upe.sisepei.config.JwtService;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.model.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;
	
	public List<User> listUsers() {
		return repository.findAll();
	}

	public User findUserProfiles(String token) throws NaoEncontradoException {
		String email = jwtService.extractUserEmail(token);
		return repository.findByEmail(email).orElseThrow(() -> new NaoEncontradoException("User not found"));
	}
	
	public User findUserById(Long id) throws NaoEncontradoException {
		return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("User not found"));
	}
	
    
    public AuthenticationResponse register(RegisterRequestDTO request) throws ValidacaoException {
        if (repository.existsByEmail(request.getEmail())) {
			throw new ValidacaoException("Email already registered");
		}
            var user = User.builder()
               .name(request.getNome())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getSenha()))
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
	
	public User updateUser(Long id, UserDTO userDTO) throws NaoEncontradoException {
		User user = findUserById(id);
		user.setName(userDTO.getName());

		return repository.save(user);
	}
	
	public void deleteUser(Long id) throws NaoEncontradoException {
		if (!repository.existsById(id)) {
			throw new NaoEncontradoException("User not found");
		}

		repository.deleteById(id);
	}

}
