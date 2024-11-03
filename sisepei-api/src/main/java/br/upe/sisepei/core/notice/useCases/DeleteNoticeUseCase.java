package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNoticeUseCase {

    private final INoticeRepository noticeRepository;

    public void execute(Long id) {
        noticeRepository.findById(id).orElseThrow(() -> new NotFoundException("Notice not found"));

        noticeRepository.deleteById(id);
    }
}
