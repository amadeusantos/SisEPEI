package br.upe.sisepei;

import br.upe.sisepei.core.notice.NoticeRepository;
import br.upe.sisepei.core.notice.NoticeService;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import br.upe.sisepei.utils.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

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
    private NoticeRepository repository;

    @Mock
    private MultipartFile file;

    private User coordinator;
    private NoticeDTO noticeDTO;
    private Notice notice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        coordinator = new User();
        noticeDTO = new NoticeDTO();
        notice = new Notice();
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
    void testCreateNotice() throws IOException, ValidationException {
        noticeDTO.setAxle(AxleEnum.EXTENSAO);
        when(repository.save(any(Notice.class))).thenReturn(notice);
        when(file.getBytes()).thenReturn(new byte[0]);

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

        when(file.getBytes()).thenReturn(new byte[0]);

        Exception exception = assertThrows(ValidationException.class, () -> {
            noticeService.createNotice(noticeDTO, coordinator, file);
        });

        assertEquals("User not authorized to create notice for axle", exception.getMessage());
    }

}
