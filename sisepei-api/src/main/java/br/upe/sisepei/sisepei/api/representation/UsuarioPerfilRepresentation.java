package br.upe.sisepei.sisepei.api.representation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UsuarioPerfilRepresentation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private UsuarioRepresentation usuario;
	
	private PerfilRepresentation perfil;
	
}
