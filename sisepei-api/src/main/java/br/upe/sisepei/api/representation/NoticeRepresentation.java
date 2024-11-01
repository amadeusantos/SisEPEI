package br.upe.sisepei.api.representation;

import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import lombok.Getter;

import java.util.Date;

@Getter
public class NoticeRepresentation {

    private final Long id;

    private final String title;

    private final String description;

    private final String requirements;

    private final Date time;

    private final AxleEnum axle;

    private final UserRepresentation coordinator;

    public NoticeRepresentation(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.description = notice.getDescription();
        this.requirements = notice.getRequirements();
        this.time = notice.getTime();
        this.axle = notice.getAxle();
        this.coordinator = new UserRepresentation(notice.getCoordinator());
    }
}
