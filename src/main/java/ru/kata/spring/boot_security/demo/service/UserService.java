package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> listUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    void update(User user);
    User getUserByEmail(String email);
}
