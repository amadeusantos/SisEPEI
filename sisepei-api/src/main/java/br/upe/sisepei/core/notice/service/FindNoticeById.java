package br.upe.sisepei.core.notice.service;

import br.upe.sisepei.core.notice.repository.NoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindNoticeById {

    private final NoticeRepository noticeRepository;

    public Notice execute(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new NotFoundException("Notice not found"));
    }
}
