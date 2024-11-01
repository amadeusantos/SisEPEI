package br.upe.sisepei.core.notice.repository;

import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.repository.interfaces.NoticeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class NoticeJPARepository implements NoticeRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private static NoticeJPARepository instance = null;

    private NoticeJPARepository() {

    }

    public static NoticeJPARepository getInstance() {
        if (instance == null) {
            instance = new NoticeJPARepository();
        }
        return instance;
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
    public void delete(Notice notice) {
        entityManager.remove(notice);
    }

    @Override
    public void deleteById(Long id) {
    
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

    @Override
    public List<Notice> listNotices() {
        String query = "SELECT n FROM Notice n";

        return entityManager
                .createQuery(query, Notice.class)
                .getResultList();
    }

    @Override
    public Notice updateNotice(Notice notice) {
        if (notice.getId() == null || findById(notice.getId()).isEmpty()) {
            throw new IllegalArgumentException("Notice ID must not be null and should exist.");
        }
        return entityManager.merge(notice);
    }
}
