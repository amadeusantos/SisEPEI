package br.upe.sisepei.core.notice.useCases;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.IUserRepository;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.ForbiddenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
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
    private Profile profile;

    @Mock
    private INoticeRepository noticeRepository;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noticeDTO = new NoticeDTO();
        profile = new Profile();
        notice = new Notice();
        file = new byte[]{1, 2, 3, 4};
        coordinator = new User();
        coordinator.setId(1L);
    }

    @Test
    void execute() {
        profile.setName(ProfileEnum.COORDENADOR_EXTENSAO);
        coordinator.setProfiles(List.of(profile));
        noticeDTO.setAxle(AxleEnum.EXTENSAO);
        when(noticeRepository.save(any(Notice.class))).thenReturn(notice);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(coordinator));

        var result = createNoticeUseCase.execute(noticeDTO, 1L, file);

        assertNotNull(result);
        verify(noticeRepository, times(1)).save(any(Notice.class));
    }

    @Test
    void execute_Forbidden() {
        profile.setName(ProfileEnum.COORDENADOR_INOVACAO);
        coordinator.setProfiles(List.of(profile));
        noticeDTO.setAxle(AxleEnum.EXTENSAO);
        when(noticeRepository.save(any(Notice.class))).thenReturn(notice);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(coordinator));

        assertThrows(ForbiddenException.class, () -> createNoticeUseCase.execute(noticeDTO, 1L, file));
    }
}