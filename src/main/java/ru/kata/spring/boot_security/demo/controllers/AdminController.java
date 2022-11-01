package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String userList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userInfo", user);
        model.addAttribute("allUsers", userService.listUsers());
        model.addAttribute("userForm", new User());
        model.addAttribute("roles", roleService.roleList());
        return "admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable Long id, @RequestParam(defaultValue = "") String action) {
        System.out.println("user id for delete: " + id);
        if (action.equals("delete")) {
            userService.deleteUser(id);
        }
        return "redirect:/admin";
    }

    @PutMapping("/admin")
    public String updateUser(@ModelAttribute("userUpdate")User user) {
        System.out.println("user for update: " + user);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("userForm") User userForm, Model model) {
        System.out.println("User info from controller: " + userForm);
        userService.saveUser(userForm);
        return "redirect:/admin";
    }
}
