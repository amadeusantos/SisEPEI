package br.upe.sisepei.core.profile.respository;

import br.upe.sisepei.core.profile.IProfileRepository;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IProfileJPARepository implements IProfileRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
