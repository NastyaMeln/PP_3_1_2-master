package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> userList = userRepository.getAllUsers();
        return userList;
    }

    @Transactional
    public void addUser(User user) {
        System.out.println(user);
        User userFromDB = userRepository.getUserByUsername(user.getUsername());

        if (userFromDB != null) {
            throw new IllegalArgumentException(String.format("User with username %s already exists in database", user.getUsername()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.addUser(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Transactional
    public void updateUser(User user, long id) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.updateUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("no such username %s", username));
        }

        return user;
    }
}
