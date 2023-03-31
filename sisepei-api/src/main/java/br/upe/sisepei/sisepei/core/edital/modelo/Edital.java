package br.upe.sisepei.sisepei.core.edital.modelo;

import java.io.File;
import java.util.Date;

import br.upe.sisepei.sisepei.core.edital.modelo.TipoEnum;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	private File edital;
	
	private TipoEnum tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Usuario")
	private Usuario coordenador;
	
}
