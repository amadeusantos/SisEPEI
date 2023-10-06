package br.upe.sisepei.core.notice.model;

import java.util.Date;

import br.upe.sisepei.core.user.model.User;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	
	private String description;
	
	private String requirements;
	
	private Date time;

	@Column(columnDefinition = "longblob")
	private byte[] file;
	
	private AxleEnum axle;
	
	@ManyToOne(optional = false)
	private User coordinator;

}
