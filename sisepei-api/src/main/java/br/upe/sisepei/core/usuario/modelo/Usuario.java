package br.upe.sisepei.core.usuario.modelo;

import java.util.Collection;
import java.util.List;

import br.upe.sisepei.core.edital.modelo.Edital;
import br.upe.sisepei.core.perfil.modelo.Perfil;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nome;

	@Column(unique = true)
	private String email;

	private String senha;

	@OneToMany(mappedBy = "coordenador")
	private List<Edital> editais;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name= "usuario_perfil",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns =  @JoinColumn(name = "perfil_id"))
	private List<Perfil> perfis;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfis;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
