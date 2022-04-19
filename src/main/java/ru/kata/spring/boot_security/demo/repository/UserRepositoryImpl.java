package ru.kata.spring.boot_security.demo.repository;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> getAllUsers() {
        List<User> userList = em.createQuery("SELECT user from User user").getResultList();
        return userList;
    }

    public void addUser(User user) {
        em.persist(user);
    }

    public User getUserById(long id) {
        User user = em.find(User.class, id);
        return user;
    }

    public void updateUser(User user) {
        em.merge(user);
    }

    public void delete(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> userList = em.createNamedQuery("findByUsername", User.class)
                .setParameter("username", username).getResultList();
        return userList.isEmpty() ? null : userList.get(0);
    }
}
