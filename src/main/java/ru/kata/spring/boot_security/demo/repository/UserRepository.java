package ru.kata.spring.boot_security.demo.repository;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    void addUser(User user);

    User getUserById(long id);

    void updateUser(User user);

    void delete(long id);

    User getUserByUsername(String username);
}
