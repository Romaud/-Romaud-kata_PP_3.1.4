package ru.kata.spring.boot_security.demo.entity;

import javax.validation.constraints.*;
import java.util.List;

public class UserDTO {
    private Long id;

    @NotBlank
    @Size(min=3, max=30)
    private String username;

    @NotBlank
    @Size(min=3, max=30)
    private String surname;

    @NotNull
    @Min(1)
    @Max(127)
    private Byte age;

    @Email(regexp = ".+[@].+[\\.].+")
    @Size(min=5, max=30)
    private String email;

    private String password;

    @NotEmpty
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String username, String surname, Byte age, String email, String password, List<String> roles) {
        this.username = username;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public Byte getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
