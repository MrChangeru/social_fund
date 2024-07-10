package com.social_fund.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String lastName;

    private String firstName;

    private String patronymic;

    private LocalDate dateOfBirth;

    private String email;

    private String password;

    private LocalDateTime dateOfCreation;

    public User() {
    }

    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public User(Long id, String login, String lastName, String firstName, String patronymic, LocalDate dateOfBirth, String email, String password, LocalDateTime dateOfCreation) {
        this.id = id;
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.dateOfCreation = dateOfCreation;
    }
}
