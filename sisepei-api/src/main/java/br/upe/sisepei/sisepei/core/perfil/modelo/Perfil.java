package br.upe.sisepei.sisepei.core.perfil.modelo;

import java.util.List;

import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;

import javax.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private PerfilEnum nome;

	@ManyToMany(mappedBy="perfis")
	private List<Usuario> usuarios;

	@Override
	public String getAuthority() {
		return nome.toString();
	}
}
