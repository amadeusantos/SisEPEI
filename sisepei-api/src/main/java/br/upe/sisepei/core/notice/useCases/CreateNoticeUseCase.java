package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.useCases.FindUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNoticeUseCase {

    private final INoticeRepository noticeRepository;

    private final FindUserByIdUseCase findUserByIdUseCase;

    public Notice execute(NoticeDTO noticeDTO, Long coordinatorId, byte[] file) { // TODO: permission
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDTO, notice);
        User coordinator = findUserByIdUseCase.execute(coordinatorId);
        notice.setCoordinator(coordinator);
        notice.setFile(file);
        return noticeRepository.save(notice);
    }
}
