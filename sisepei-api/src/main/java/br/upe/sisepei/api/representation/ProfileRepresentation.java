package br.upe.sisepei.api.representation;

import java.io.Serializable;

import br.upe.sisepei.core.profile.model.ProfileEnum;

import lombok.Data;

@Data
public class ProfileRepresentation implements Serializable {
	
	private ProfileEnum name;
	
}

