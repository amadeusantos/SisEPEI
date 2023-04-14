package br.upe.sisepei.sisepei.core.edital.modelo;

import java.io.File;
import java.util.Date;

//import br.upe.sisepei.sisepei.core.edital.modelo.TipoEnum;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditalDTO {
	
	@NotBlank
	private String titulo;

	@NotBlank
	private String descricao;
	
	@NotBlank
	private String requisitos;
	
	@NotBlank
	private File edital;
	
	@NotBlank
	private Date prazo;
	
	@NotBlank
	private TipoEnum tipo;
	
	@NotBlank
	private Usuario coordenador;

}
