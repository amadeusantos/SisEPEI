package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ListNoticesUseCaseTest {

    @InjectMocks
    private ListNoticesUseCase listNoticesUseCase;

    @Mock
    private INoticeRepository noticeRepository;

    private List<Notice> notices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notices = new ArrayList<>();
        notices.add(new Notice());
        notices.add(new Notice());
        notices.add(new Notice());
    }

    @Test
    void execute() {
        when(noticeRepository.findAll()).thenReturn(notices);

        List<Notice> result = listNoticesUseCase.execute();

        assertNotNull(result);
        assertEquals(notices.size(), result.size());
    }
}