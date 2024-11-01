package br.upe.sisepei.core.notice.service.factories;

import br.upe.sisepei.core.notice.repository.NoticeJPARepository;
import br.upe.sisepei.core.notice.service.CreateNotice;
import br.upe.sisepei.core.notice.service.FindNoticeById;

public class CreateNoticeJPA {
    public CreateNotice handle() {
        NoticeJPARepository repository = NoticeJPARepository.getInstance();
        return new CreateNotice(repository);
    }
}
