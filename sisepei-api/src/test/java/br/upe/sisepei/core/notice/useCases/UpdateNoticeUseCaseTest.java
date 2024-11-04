package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
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
import static org.mockito.Mockito.when;

class UpdateNoticeUseCaseTest {

    @InjectMocks
    private UpdateNoticeUseCase updateNoticeUseCase;

    @Mock
    private INoticeRepository noticeRepository;

    @Mock
    private IUserRepository userRepository;

    private Notice notice;
    private NoticeDTO noticeDTO;
    private byte[] file;
    private User coordinator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notice = new Notice();
        notice.setId(1L);
        notice.setTitle("Test");
        coordinator = new User();
        coordinator.setId(1L);
        noticeDTO = new NoticeDTO();
        noticeDTO.setTitle("Test2");
        file = new byte[]{1, 2, 3};
    }

    @Test
    void execute() {
        when(noticeRepository.findById(1L)).thenReturn(Optional.ofNullable(notice));
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(coordinator));
        when(noticeRepository.save(notice)).thenReturn(notice);

        var result = updateNoticeUseCase.execute(1L, noticeDTO, 1L, file);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test2", result.getTitle());
    }
}
