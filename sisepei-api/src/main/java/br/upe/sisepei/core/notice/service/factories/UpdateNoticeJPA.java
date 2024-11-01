package br.upe.sisepei.core.notice.service.factories;

import br.upe.sisepei.core.notice.repository.NoticeJPARepository;
import br.upe.sisepei.core.notice.service.FindNoticeById;
import br.upe.sisepei.core.notice.service.UpdateNotice;

public class UpdateNoticeJPA {

    public UpdateNotice handle() {
        NoticeJPARepository repository = NoticeJPARepository.getInstance();
        FindNoticeById findNoticeByIdService = new FindNoticeById(repository);
        return new UpdateNotice(repository, findNoticeByIdService);
    }
}