package br.upe.sisepei.core.notice.repository;

import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.INoticeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class NoticeJPARepository implements INoticeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Notice> findAll() {
        String query = "SELECT n FROM User n";

        return entityManager
                .createQuery(query, Notice.class)
                .getResultList();
    }

    @Override
    public Notice save(Notice notice) {
        entityManager.persist(notice);
        return notice;
    }

    @Override
    public List<Notice> findAllByAxle(AxleEnum axle) {
        String query = "SELECT n FROM Notice n WHERE n.axle = :axle";
        return entityManager
                .createQuery(query, Notice.class)
                .setParameter("axle", axle)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM Notice n WHERE n.id = :id";
        entityManager
                .createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Optional<Notice> findById(Long id) {
        String query = "SELECT n FROM Notice n WHERE n.id = :id";
        var entity = entityManager
                .createQuery(query, Notice.class)
                .setParameter("id", id)
                .getSingleResult();
        
        return Optional.ofNullable(entity);
    }
}
