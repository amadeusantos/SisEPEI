package br.upe.sisepei.core.notice.model;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NoticeDTO {

	@NotBlank(message = "Enter a title")
	private String title;

	@NotBlank(message = "Enter a description")
	private String description;

	private String requirements;

	@NotNull(message = "Enter a time valid")
	private Date time;

	@NotNull(message = "Enter a axle valid")
	private AxleEnum axle;

	@NotNull(message = "Enter a valid file")
	private MultipartFile file;

}
