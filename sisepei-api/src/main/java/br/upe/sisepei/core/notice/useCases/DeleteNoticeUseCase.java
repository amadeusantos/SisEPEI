package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import br.upe.sisepei.utils.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNoticeUseCase {

    private final INoticeRepository noticeRepository;

    public void execute(Long id, User user) {
        noticeRepository.findById(id).orElseThrow(() -> new NotFoundException("Notice not found"));

        if (noticeRepository.findById(id).isPresent() && noticeRepository.findById(id).get().getCoordinator() != user) {
            throw new UnauthorizedException("Você não tem autorização para deletar o edital");
        }
        noticeRepository.deleteById(id);
    }
}
