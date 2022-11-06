package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.entity.UserDTO;
import ru.kata.spring.boot_security.demo.exception_handling.UserException;
import ru.kata.spring.boot_security.demo.exception_handling.UserIncorrectData;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/admin")
@RestController
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> userList() {
        return userService.listUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.getUserByEmail(userDTO.getEmail());
        if (user != null) {
            throw new UserException("Пользователь с таким email уже существует!");
        } else {
            user = getUser(userDTO);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDTO userDTO) {
        User user = getUser(userDTO);
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    private User getUser(UserDTO userDTO) {
        Set<Role> roleSet = new HashSet<>();
        userDTO.getRoles().forEach(e -> {
            roleSet.add(roleService.getByName("ROLE_".concat(e)));
        });
        User user = new User(userDTO.getUsername(), userDTO.getSurname(),
                userDTO.getAge(), userDTO.getEmail(), userDTO.getPassword(), roleSet);
        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        return user;
    }
}
