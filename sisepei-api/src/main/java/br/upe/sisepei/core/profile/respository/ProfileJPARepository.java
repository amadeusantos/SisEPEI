package br.upe.sisepei.core.profile.respository;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.profile.respository.interfaces.ProfileRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class ProfileJPARepository implements ProfileRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private static ProfileJPARepository instance = null;

    private ProfileJPARepository() {

    }

    public static ProfileJPARepository getInstance() {
        if (instance == null) {
            instance = new ProfileJPARepository();
        }
        return instance;
    }


    @Override
    public Optional<Profile> findByName(ProfileEnum name) {
        String query = "SELECT p FROM Profile p WHERE p.name = :name";
        Profile profile = entityManager
                .createQuery(query, Profile.class)
                .setParameter("name", name)
                .getSingleResult();

        return Optional.ofNullable(profile);
    }

    @Override
    public List<Profile> findAll() {
        String query = "SELECT p FROM Profile p";
        return entityManager
                .createQuery(query, Profile.class)
                .getResultList();
    }
}
