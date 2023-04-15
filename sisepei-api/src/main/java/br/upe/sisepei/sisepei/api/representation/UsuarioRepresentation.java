package br.upe.sisepei.sisepei.api.representation;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UsuarioRepresentation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String email;

	private List<PerfilRepresentation> perfis;

	@JsonIgnore
	private List<EditalRepresentation> editais;

}

