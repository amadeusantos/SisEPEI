package br.upe.sisepei.core.notice.service;

import br.upe.sisepei.core.notice.repository.interfaces.NoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;


@RequiredArgsConstructor
public class CreateNotice {

    private final NoticeRepository noticeRepository;

    public Notice execute(NoticeDTO noticeDTO, User coordinator, byte[] file) {

        if (RoleVerifier.execute(noticeDTO.getAxle(), coordinator)) {
            throw  new ValidationException("User not authorized to create notice for axle");
        }

        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDTO, notice);
        notice.setCoordinator(coordinator);
        notice.setFile(file);
        return noticeRepository.save(notice);
    }
}