package br.upe.sisepei.core.notice.service;


import br.upe.sisepei.core.notice.repository.NoticeRepository;
import br.upe.sisepei.core.notice.model.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListNotices {

    private final NoticeRepository noticeRepository;

    public List<Notice> execute(){
        return noticeRepository.findAll();
    }
}
