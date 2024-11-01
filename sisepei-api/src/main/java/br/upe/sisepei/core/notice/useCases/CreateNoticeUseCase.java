package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CreateNoticeUseCase {
    private final INoticeRepository noticeRepository;

    public Notice execute(NoticeDTO noticeDTO, User coordinator, byte[] file) { // TODO: permission
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDTO, notice);
        notice.setCoordinator(coordinator);
        notice.setFile(file);
        return noticeRepository.save(notice);
    }
}
