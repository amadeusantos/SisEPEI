package br.upe.sisepei.core.notice.service.factories;

import br.upe.sisepei.core.notice.repository.NoticeJPARepository;
import br.upe.sisepei.core.notice.service.CreateNotice;
import br.upe.sisepei.core.notice.service.FindNoticeById;

public class FindNoticeByIdJPA {

    public FindNoticeById handle() {
        NoticeJPARepository repository = NoticeJPARepository.getInstance();
        return new FindNoticeById(repository);
    }
}
