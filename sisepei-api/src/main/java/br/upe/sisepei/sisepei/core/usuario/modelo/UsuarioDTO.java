package br.upe.sisepei.sisepei.core.usuario.modelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 6)
	private String senha;

}
