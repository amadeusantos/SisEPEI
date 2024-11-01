package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindNoticeByIdUseCase {

    private final INoticeRepository noticeRepository;

    public Notice execute(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new NotFoundException("Notice not found"));
    }
}
