package br.upe.sisepei.sisepei.core.usuario.modelo;

import java.util.List;

import br.upe.sisepei.sisepei.core.usuarioPerfil.modelo.UsuarioPerfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<UsuarioPerfil> perfis;
	
//	@OneToMany(mappedBy = "Edital")
//	private List<Edital> editais;
	
}
