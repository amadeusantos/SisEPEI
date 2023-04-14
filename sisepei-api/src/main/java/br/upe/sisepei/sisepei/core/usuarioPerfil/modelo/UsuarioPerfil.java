package br.upe.sisepei.sisepei.core.usuarioPerfil.modelo;

import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class UsuarioPerfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Perfil perfil;

}
