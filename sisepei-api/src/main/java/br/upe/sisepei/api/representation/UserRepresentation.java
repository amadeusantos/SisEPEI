package br.upe.sisepei.api.representation;

import java.util.List;
import lombok.Data;

@Data
public class UserRepresentation {

	private Long id;
	
	private String name;
	
	private String email;

	private List<ProfileRepresentation> profiles;

}

