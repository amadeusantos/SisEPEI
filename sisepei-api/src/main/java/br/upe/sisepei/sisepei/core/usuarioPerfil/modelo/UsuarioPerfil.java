package br.upe.sisepei.sisepei.core.usuarioPerfil.modelo;

import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
