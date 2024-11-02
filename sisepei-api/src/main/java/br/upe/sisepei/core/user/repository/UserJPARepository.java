package br.upe.sisepei.core.user.repository;

import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserJPARepository implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByEmail(String email) {
        String query = "SELECT 1 FROM User u WHERE u.email = :email";
        List<Integer> result = entityManager.createQuery(query, Integer.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList();

        return !result.isEmpty();
    }

    @Override
    public boolean existsById(Long id) {
        String query = "SELECT 1 FROM User u WHERE u.id = :id";
        List<Integer> result = entityManager.createQuery(query, Integer.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList();

        return !result.isEmpty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT u FROM User u WHERE u.email = :email";
        List<User> users = entityManager
                .createQuery(query, User.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList();

        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT u FROM User u WHERE u.id = :id";
        List<User> users = entityManager
                .createQuery(query, User.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList();

        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT u FROM User u";

        return entityManager
                .createQuery(query, User.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM User u WHERE u.id = :id";
        entityManager
                .createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}