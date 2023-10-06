package br.upe.sisepei.core.profile.model;

import java.util.List;

import br.upe.sisepei.core.usuario.modelo.Usuario;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
public class Profile implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private ProfileEnum name;

	@ManyToMany(mappedBy= "profiles")
	private List<Usuario> users;

	@Override
	public String getAuthority() {
		return name.toString();
	}
}