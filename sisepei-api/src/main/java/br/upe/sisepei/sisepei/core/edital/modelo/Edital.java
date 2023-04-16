package br.upe.sisepei.sisepei.core.edital.modelo;

import java.util.Date;

import javax.persistence.*;

import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import lombok.Data;


@Entity
@Data
public class Edital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String titulo;
	
	private String descricao;
	
	private String requisitos;
	
	private Date prazo;

	@Column(columnDefinition = "longblob")
	private byte[] edital;
	
	private TipoEnum tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Usuario")
	private Usuario coordenador;
	
}
