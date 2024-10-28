package br.upe.sisepei.core.notice.model;

import java.util.Base64;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

	@NotBlank(message = "")
	private String file;

}
