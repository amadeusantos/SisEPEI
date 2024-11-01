package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateNoticeUseCase {

    private final INoticeRepository noticeRepository;

    private final FindNoticeByIdUseCase findNoticeByIdUseCase;

    public Notice execute(Long id, NoticeDTO noticeDTO, User coordinator, byte[] file) { // TODO: permission
        Notice notice = findNoticeByIdUseCase.execute(id);
        BeanUtils.copyProperties(noticeDTO, notice);
        notice.setCoordinator(coordinator);
        notice.setFile(file);
        return noticeRepository.save(notice);
    }
}
