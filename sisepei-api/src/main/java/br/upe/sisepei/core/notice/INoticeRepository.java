package br.upe.sisepei.core.notice;

import br.upe.sisepei.core.notice.model.AxleEnum;

import br.upe.sisepei.core.notice.model.Notice;

import java.util.List;
import java.util.Optional;

public interface INoticeRepository {
    List<Notice> findAll();
    Notice save(Notice notice);
    List<Notice> findAllByAxle(AxleEnum axle);
    void deleteById(Long id);
    Optional<Notice> findById(Long id);
}
