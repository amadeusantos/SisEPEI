package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindNoticeByIdUseCaseTest {

    @InjectMocks
    private FindNoticeByIdUseCase findNoticeByIdUseCase;

    private Notice notice;

    @Mock
    private INoticeRepository noticeRepository;

    @BeforeEach
    void setUp() {
        this.notice = new Notice();
        notice.setId(1L);
    }

    @Test
    void execute() {
        when(noticeRepository.findById(1L)).thenReturn(Optional.ofNullable(notice));

        findNoticeByIdUseCase.execute(1L);

        verify(noticeRepository, times(1)).findById(1L);
    }

    @Test
    void executeNotFound() {
        when(noticeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> findNoticeByIdUseCase.execute(2L));
    }
}