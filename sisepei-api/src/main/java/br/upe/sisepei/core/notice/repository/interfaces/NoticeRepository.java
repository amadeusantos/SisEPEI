package br.upe.sisepei.core.notice.repository.interfaces;

import br.upe.sisepei.core.notice.model.AxleEnum;

import br.upe.sisepei.core.notice.model.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository{

    Notice save(Notice notice);
    List<Notice> findAllByAxle(AxleEnum axle);
    void delete(Notice notice);
    void deleteById(Long id);
    Optional<Notice> findById(Long id);
    List<Notice> listNotices();
    Notice updateNotice(Notice notice);
}
