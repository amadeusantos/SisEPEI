package br.upe.sisepei.core.notice.service.factories;

import br.upe.sisepei.core.notice.repository.NoticeJPARepository;
import br.upe.sisepei.core.notice.repository.interfaces.NoticeRepository;
import br.upe.sisepei.core.notice.service.FindNoticesByAxle;
import br.upe.sisepei.core.notice.service.ListNotices;

public class ListNoticesJPA {

    public ListNotices handle() {
        NoticeRepository repository = NoticeJPARepository.getInstance();
        return new ListNotices(repository);
    }
}
