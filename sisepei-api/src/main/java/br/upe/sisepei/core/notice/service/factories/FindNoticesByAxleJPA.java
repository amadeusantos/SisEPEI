package br.upe.sisepei.core.notice.service.factories;

import br.upe.sisepei.core.notice.repository.NoticeJPARepository;
import br.upe.sisepei.core.notice.service.FindNoticesByAxle;

public class FindNoticesByAxleJPA {

    public FindNoticesByAxle handle() {
        NoticeJPARepository repository = NoticeJPARepository.getInstance();
        return new FindNoticesByAxle(repository);
    }
}
