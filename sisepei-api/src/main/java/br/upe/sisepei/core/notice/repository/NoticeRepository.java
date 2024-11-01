package br.upe.sisepei.core.notice.repository;

import br.upe.sisepei.core.notice.model.AxleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.core.notice.model.Notice;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByAxle(AxleEnum axle);

}
