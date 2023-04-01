package br.upe.sisepei.sisepei.core.perfil.modelo;

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

@Data
@Entity
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	@OneToMany(mappedBy = "perfil", fetch = FetchType.EAGER)
	private List<UsuarioPerfil> usuarios; 
	
}
