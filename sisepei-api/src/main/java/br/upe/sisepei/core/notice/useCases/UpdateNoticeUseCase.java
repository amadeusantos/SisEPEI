package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import br.upe.sisepei.utils.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateNoticeUseCase {

    private final INoticeRepository noticeRepository;
    private final IUserRepository userRepository;

    public Notice execute(Long id, NoticeDTO noticeDTO, Long coordinatorId, byte[] file) { // TODO: permission
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new NotFoundException("Notice not found"));

        if (!Objects.equals(notice.getCoordinator().getId(), coordinatorId)) {
            throw new UnauthorizedException("Coordinator does not belong to this notice");
        }

        BeanUtils.copyProperties(noticeDTO, notice);
        notice.setFile(file);
        return noticeRepository.save(notice);
    }
}
