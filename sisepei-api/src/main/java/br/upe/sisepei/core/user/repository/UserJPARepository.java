package br.upe.sisepei.core.user.repository;

import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.core.user.repository.interfaces.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class UserJPARepository implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private static UserJPARepository instance = null;

    private UserJPARepository() {}

    public static UserJPARepository getInstance() {
        if (instance == null) {
            instance = new UserJPARepository();
        }
        return instance;
    }

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
        User user = entityManager
                .createQuery(query, User.class)
                .setParameter("email", email)
                .getSingleResult();

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT u FROM User u WHERE id = :id";
        User user = entityManager
                .createQuery(query, User.class)
                .setParameter("id", id)
                .getSingleResult();

        return Optional.ofNullable(user);
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

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}