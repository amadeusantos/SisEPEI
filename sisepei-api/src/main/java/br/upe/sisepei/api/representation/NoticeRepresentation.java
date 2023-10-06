package br.upe.sisepei.api.representation;

import br.upe.sisepei.core.notice.model.AxleEnum;
import lombok.Data;

import java.util.Date;

@Data
public class NoticeRepresentation {

    private Long id;

    private String title;

    private String description;

    private String requirements;

    private Date time;

    private AxleEnum axle;

    private UsuarioRepresentation coordinator;

}
