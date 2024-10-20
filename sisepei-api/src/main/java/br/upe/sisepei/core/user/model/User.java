package br.upe.sisepei.core.user.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.profile.model.Profile;
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
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	@OneToMany(mappedBy = "coordinator")
	private List<Notice> notices;

	@ManyToMany
	@JoinTable(name= "users_profiles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns =  @JoinColumn(name = "profile_id"))
	private List<Profile> profiles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return profiles;
	}

	@Override
	public String getPassword() {
		return password;
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

	public List<Profile> getProfiles() {
		return profiles != null ? profiles : Collections.emptyList();
	}

}
