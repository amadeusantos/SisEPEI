package br.upe.sisepei.core.notice.model;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDTO {

	private String title;

	private String description;

	private String requirements;

	private Date time;

	private AxleEnum axle;

}
