package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                             @RequestParam(defaultValue = "") String action) {
        System.out.println("user id: " + userId);
        System.out.println("action: " + action);
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/get/{userId}")
    public String updateUser(@ModelAttribute("userUpdate")User user) {
        userService.update(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/get/{userId}")
    public String  getUser(@PathVariable("userId") Long userId, Model model) {
        //model.addAttribute("allUsers", userService.getUserById(userId));
        model.addAttribute("userUpdate", userService.getUserById(userId));
        return "update";
    }
}
