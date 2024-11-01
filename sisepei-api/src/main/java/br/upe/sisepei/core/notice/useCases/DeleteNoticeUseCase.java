package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNoticeUseCase {

    private final INoticeRepository noticeRepository;
    private final FindNoticeByIdUseCase findNoticeByIdUseCase;

    public void execute(Long id, Long coordinatorId) {
        findNoticeByIdUseCase.execute(id);
        noticeRepository.deleteById(id);
    }
}
