package br.upe.sisepei.sisepei.core.perfil.modelo;

import java.util.List;

import br.upe.sisepei.sisepei.core.usuarioPerfil.modelo.UsuarioPerfil;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
