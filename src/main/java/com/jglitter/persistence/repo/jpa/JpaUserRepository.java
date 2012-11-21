package com.jglitter.persistence.repo.jpa;

import com.jglitter.domain.User;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Uses JPA entity manager to persist users.
 */
@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User persist(final User user) {
        em.persist(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select d from User d", User.class).getResultList();
    }

    @Override
    public User findByUuid(final String id) {
        final TypedQuery<User> query = em.createQuery("select u from User u where u.uuid = :user_uuid", User.class);
        final List<User> users = query.setParameter("user_uuid", id).getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void deleteAll() {
        List<User> users = em.createQuery("select d from User d", User.class).getResultList();
        for (User eachUser : users) {
            em.remove(eachUser);
        }
    }
}
