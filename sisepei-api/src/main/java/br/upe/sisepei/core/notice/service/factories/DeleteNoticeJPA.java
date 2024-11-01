package br.upe.sisepei.core.notice.service.factories;

import br.upe.sisepei.core.notice.repository.NoticeJPARepository;
import br.upe.sisepei.core.notice.service.DeleteNotice;
import br.upe.sisepei.core.notice.service.FindNoticeById;

public class DeleteNoticeJPA {

    public DeleteNotice handle() {
        NoticeJPARepository repository = NoticeJPARepository.getInstance();
        FindNoticeById findNoticeById = new FindNoticeById(repository);

        return new DeleteNotice(repository, findNoticeById);
    }
}
