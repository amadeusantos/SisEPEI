package br.upe.sisepei.core.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

	@NotBlank
	private String name;

}
