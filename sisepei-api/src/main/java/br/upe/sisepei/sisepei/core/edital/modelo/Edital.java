package br.upe.sisepei.sisepei.core.edital.modelo;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	private File edital;
	
	private TipoEnum tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Usuario")
	private Usuario coordenador;
	
}
