package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateNoticeUseCaseTest {

    @InjectMocks
    private CreateNoticeUseCase createNoticeUseCase;

    private NoticeDTO noticeDTO;
    private Notice notice;
    private byte[] file;
    private User coordinator;

    @Mock
    private INoticeRepository noticeRepository;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noticeDTO = new NoticeDTO();
        notice = new Notice();
        file = new byte[]{1, 2, 3, 4};
        coordinator = new User();
    }

    @Test
    void execute() {
        noticeDTO.setAxle(AxleEnum.EXTENSAO);
        when(noticeRepository.save(any(Notice.class))).thenReturn(notice);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(coordinator));

        var result = createNoticeUseCase.execute(noticeDTO, coordinator.getId(), file);

        assertNotNull(result);
        verify(noticeRepository, times(1)).save(any(Notice.class));
    }
}