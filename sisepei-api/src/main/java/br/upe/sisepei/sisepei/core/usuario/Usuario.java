package br.upe.sisepei.sisepei.core.usuario;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private List<PerfilEnum> perfis;
	
//	@OneToMany(mappedBy = "Edital")
//	private List<Edital> editais;
	
}
