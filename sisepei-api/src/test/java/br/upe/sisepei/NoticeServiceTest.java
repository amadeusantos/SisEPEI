package br.upe.sisepei;

import br.upe.sisepei.core.notice.INoticeRepository;
import br.upe.sisepei.core.notice.NoticeService;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import br.upe.sisepei.utils.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static br.upe.sisepei.core.profile.model.ProfileEnum.COORDENADOR_EXTENSAO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoticeServiceTest {

    @InjectMocks
    private NoticeService noticeService;

    @Mock
    private INoticeRepository repository;

    private byte[] file;
    private User coordinator;
    private NoticeDTO noticeDTO;
    private Notice notice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        coordinator = new User();
        noticeDTO = new NoticeDTO();
        notice = new Notice();
        file = new byte[]{1, 2, 3, 4};
    }

    @Test
    void testListNotices() {
        when(repository.findAll()).thenReturn(Collections.singletonList(notice));

        var notices = noticeService.listNotices();

        assertNotNull(notices);
        assertEquals(1, notices.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindNoticeById() {
        when(repository.findById(1L)).thenReturn(Optional.of(notice));

        var result = noticeService.findNoticeById(1L);

        assertEquals(notice, result);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindNoticeById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            noticeService.findNoticeById(1L);
        });

        assertEquals("Notice not found", exception.getMessage());
    }

    @Test
    void testCreateNotice() throws IOException, UnprocessableEntityException {
        noticeDTO.setAxle(AxleEnum.EXTENSAO);
        when(repository.save(any(Notice.class))).thenReturn(notice);

        var result = noticeService.createNotice(noticeDTO, coordinator, file);

        assertNotNull(result);
        verify(repository, times(1)).save(any(Notice.class));
    }

    @Test
    void testCreateNotice_UserAuthorized() throws IOException {
        noticeDTO.setAxle(AxleEnum.EXTENSAO);

        Profile profile = new Profile();
        ArrayList<User> users = new ArrayList<>();
        users.add(coordinator);
        profile.setUsers(users);
        profile.setName(COORDENADOR_EXTENSAO);
        coordinator.setProfiles(Collections.singletonList(profile));

        Exception exception = assertThrows(UnprocessableEntityException.class, () -> {
            noticeService.createNotice(noticeDTO, coordinator, file);
        });

        assertEquals("User not authorized to create notice for axle", exception.getMessage());
    }

}
