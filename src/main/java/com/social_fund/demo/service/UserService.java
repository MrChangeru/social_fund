package com.social_fund.demo.service;

import com.social_fund.demo.model.User;
import com.social_fund.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsersWithFilters(Map<String, String> filters) {
        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> {
                    boolean matches = true;
                    if (filters.containsKey("ID") && !filters.get("ID").isEmpty()) {
                        matches = matches && user.getId().toString().contains(filters.get("ID"));
                    }
                    if (filters.containsKey("Login") && !filters.get("Login").isEmpty()) {
                        matches = matches && user.getLogin().contains(filters.get("Login"));
                    }
                    if (filters.containsKey("Last Name") && !filters.get("Last Name").isEmpty()) {
                        matches = matches && user.getLastName().contains(filters.get("Last Name"));
                    }
                    if (filters.containsKey("First Name") && !filters.get("First Name").isEmpty()) {
                        matches = matches && user.getFirstName().contains(filters.get("First Name"));
                    }
                    if (filters.containsKey("Patronymic") && !filters.get("Patronymic").isEmpty()) {
                        matches = matches && user.getPatronymic().contains(filters.get("Patronymic"));
                    }
                    if (filters.containsKey("Date of Birth") && !filters.get("Date of Birth").isEmpty()) {
                        LocalDateTime now = LocalDateTime.now();
                        switch (filters.get("Date of Birth")) {
                            case "day":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusDays(1));
                                break;
                            case "week":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusWeeks(1));
                                break;
                            case "month":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusMonths(1));
                                break;
                            case "year":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusYears(1));
                                break;
                            case "custom":
                                LocalDate startDate = LocalDate.parse(filters.get("Date of Birth_start"));
                                LocalDate endDate = LocalDate.parse(filters.get("Date of Birth_end"));
                                matches = matches && (user.getDateOfCreation().toLocalDate().isAfter(startDate) || user.getDateOfCreation().toLocalDate().isEqual(startDate))
                                        && (user.getDateOfCreation().toLocalDate().isBefore(endDate) || user.getDateOfCreation().toLocalDate().isEqual(endDate));
                                break;
                        }
                    }
                    if (filters.containsKey("Email") && !filters.get("Email").isEmpty()) {
                        matches = matches && user.getEmail().contains(filters.get("Email"));
                    }
                    if (filters.containsKey("Date of Creation") && !filters.get("Date of Creation").isEmpty()) {
                        LocalDateTime now = LocalDateTime.now();
                        switch (filters.get("Date of Creation")) {
                            case "day":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusDays(1));
                                break;
                            case "week":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusWeeks(1));
                                break;
                            case "month":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusMonths(1));
                                break;
                            case "year":
                                matches = matches && user.getDateOfCreation().isAfter(now.minusYears(1));
                                break;
                            case "custom":
                                LocalDate startDate = LocalDate.parse(filters.get("Date of Creation_start"));
                                LocalDate endDate = LocalDate.parse(filters.get("Date of Creation_end"));
                                matches = matches && (user.getDateOfCreation().toLocalDate().isAfter(startDate) || user.getDateOfCreation().toLocalDate().isEqual(startDate))
                                        && (user.getDateOfCreation().toLocalDate().isBefore(endDate) || user.getDateOfCreation().toLocalDate().isEqual(endDate));
                                break;
                        }
                    }
                    return matches;
                })
                .collect(Collectors.toList());
    }
}
