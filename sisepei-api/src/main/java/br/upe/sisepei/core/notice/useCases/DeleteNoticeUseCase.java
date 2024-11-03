package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNoticeUseCase {

    private final INoticeRepository noticeRepository;
    private final IUserRepository userRepository;

    public void execute(Long id, Long coordinatorId) {
        userRepository.findById(coordinatorId).orElseThrow(() -> new NotFoundException("Coordinator not found"));

        noticeRepository.deleteById(id);
    }
}
