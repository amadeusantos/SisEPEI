package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNoticeUseCase {

    private final INoticeRepository noticeRepository;
    private final IUserRepository userRepository;

    public Notice execute(NoticeDTO noticeDTO, Long coordinatorId, byte[] file) { // TODO: permission
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDTO, notice);
        User coordinator = userRepository.findById(coordinatorId).orElseThrow(() -> new NotFoundException("Coordinator not found"));

        notice.setCoordinator(coordinator);
        notice.setFile(file);
        return noticeRepository.save(notice);
    }
}
