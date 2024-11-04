package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FindNoticesByAxleUseCaseTest {

    @InjectMocks
    private FindNoticesByAxleUseCase findNoticesByAxleUseCase;

    @Mock
    private INoticeRepository noticeRepository;

    private ArrayList<Notice> notices;
    private Notice notice1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notice1 = new Notice();
        Notice notice2 = new Notice();
        notice1.setAxle(AxleEnum.EXTENSAO);
        notice2.setAxle(AxleEnum.PESQUISA);
        notices = new ArrayList<>(List.of(notice1, notice2));
    }

    @Test
    void execute() {
        when(noticeRepository.findAllByAxle(AxleEnum.EXTENSAO)).thenReturn(List.of(notice1));

        var result = findNoticesByAxleUseCase.execute(AxleEnum.EXTENSAO);

        assertEquals(1, result.size());
    }
}