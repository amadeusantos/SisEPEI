package br.upe.sisepei.core.user;

import java.util.List;

import br.upe.sisepei.utils.exceptions.NotFoundException;
import br.upe.sisepei.utils.exceptions.ValidationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.upe.sisepei.core.user.model.LoginDTO;
import br.upe.sisepei.api.representation.AuthenticationResponse;
import br.upe.sisepei.core.user.model.RegisterDTO;
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

	public User findUserProfiles(String token) throws NotFoundException {
		String email = jwtService.extractUserEmail(token);
		return repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
	}
	
	public User findUserById(Long id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
	}
	
    
    public AuthenticationResponse register(RegisterDTO request) {
        if (repository.existsByEmail(request.getEmail())) {
			throw new ValidationException("Email already registered");
		}
            var user = User.builder()
               .name(request.getName())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .build();
           repository.save(user);
           var jwtToken = jwtService.generateToken(user);
           return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
        
    }

	public AuthenticationResponse authenticate(LoginDTO request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail())
			.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
			.token(jwtToken)
			.build();
}
	
	public User updateUser(Long id, UserDTO userDTO) {
		User user = findUserById(id);
		user.setName(userDTO.getName());

		return repository.save(user);
	}
	
	public void deleteUser(Long id) {
		if (!repository.existsById(id)) {
			throw new NotFoundException("User not found");
		}

		repository.deleteById(id);
	}

}
