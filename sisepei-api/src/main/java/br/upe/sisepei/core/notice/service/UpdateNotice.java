package br.upe.sisepei.core.notice.service;

import br.upe.sisepei.core.notice.repository.NoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UpdateNotice {

    private final NoticeRepository noticeRepository;
    private final FindNoticeById findNoticeById;

    public Notice execute(Long id, NoticeDTO noticeDTO, User coordinator, byte[] file) {
        Notice notice = findNoticeById.execute(id);

        if (!notice.getCoordinator().getEmail().equals(coordinator.getEmail())) {
            throw  new ValidationException("User not authorized to edit notice");
        }

        BeanUtils.copyProperties(noticeDTO, notice);

        notice.setFile(file);

        return noticeRepository.save(notice);
    }
}
