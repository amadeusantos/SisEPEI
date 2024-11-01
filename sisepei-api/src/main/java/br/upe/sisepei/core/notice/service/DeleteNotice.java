package br.upe.sisepei.core.notice.service;


import br.upe.sisepei.core.notice.repository.NoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNotice {
    private final NoticeRepository noticeRepository;
    private final FindNoticeById findNoticeById;

    public void execute(Long id, User coordinator) {

        Notice notice = findNoticeById.execute(id);

        if (!notice.getCoordinator().getEmail().equals(coordinator.getEmail())) {
            throw new ValidationException("User not authorized to delete notice");
        }
        noticeRepository.deleteById(id);
    }
}
