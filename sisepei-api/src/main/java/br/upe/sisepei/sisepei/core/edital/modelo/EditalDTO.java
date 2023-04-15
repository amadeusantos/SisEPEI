package br.upe.sisepei.sisepei.core.edital.modelo;

import java.io.File;
import java.util.Date;

//import br.upe.sisepei.sisepei.core.edital.modelo.TipoEnum;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditalDTO {

	private String titulo;

	private String descricao;

	private String requisitos;

	private byte[] edital;

	private Date prazo;

	private TipoEnum tipo;

	private Usuario coordenador;

}
