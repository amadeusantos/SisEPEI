package br.upe.sisepei.sisepei.api.representation;

import br.upe.sisepei.sisepei.core.edital.modelo.TipoEnum;
import lombok.Data;

import java.util.Date;

@Data
public class EditalRepresentation {

    private Long id;

    private String titulo;

    private String descricao;

    private String requisitos;

    private Date prazo;

    private TipoEnum tipo;

    private UsuarioRepresentation coordenador;

}
