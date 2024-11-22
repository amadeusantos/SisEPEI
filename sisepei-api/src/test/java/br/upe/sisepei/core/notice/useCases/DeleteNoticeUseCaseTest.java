package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteNoticeUseCaseTest {

    @InjectMocks
    private DeleteNoticeUseCase deleteNoticeUseCase;

    private Notice notice;

    @Mock
    private INoticeRepository noticeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.notice = new Notice();
        this.notice.setId(1L);
    }

    @Test
    void execute() {
        User user = new User();
        when(noticeRepository.findById(1L)).thenReturn(Optional.of(notice));

        deleteNoticeUseCase.execute(1L, user);

        verify(noticeRepository, times(1)).deleteById(1L);
    }

    @Test
    void execute_noticeNotFound() {

        User user = new User();
        when(noticeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            deleteNoticeUseCase.execute(2L, user);
        });
    }
}