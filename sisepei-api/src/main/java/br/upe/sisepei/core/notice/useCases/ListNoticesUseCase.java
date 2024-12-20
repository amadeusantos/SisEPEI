package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListNoticesUseCase {

    private final INoticeRepository noticeRepository;

    public List<Notice> execute() {
        return noticeRepository.findAll();
    }
}
