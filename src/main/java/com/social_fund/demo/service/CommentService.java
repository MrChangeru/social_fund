package com.social_fund.demo.service;

import com.social_fund.demo.model.Comment;
import com.social_fund.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> findCommentsWithFilters(Map<String, String> filters) {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .filter(comment -> {
                    boolean matches = true;
                    if (filters.containsKey("ID") && !filters.get("ID").isEmpty()) {
                        matches = matches && comment.getId().toString().contains(filters.get("ID"));
                    }
                    if (filters.containsKey("Article ID") && !filters.get("Article ID").isEmpty()) {
                        matches = matches && comment.getArticleId().toString().contains(filters.get("Article ID"));
                    }
                    if (filters.containsKey("User ID") && !filters.get("User ID").isEmpty()) {
                        matches = matches && comment.getUserId().toString().contains(filters.get("User ID"));
                    }
                    if (filters.containsKey("Content") && !filters.get("Content").isEmpty()) {
                        matches = matches && comment.getContent().contains(filters.get("Content"));
                    }
                    if (filters.containsKey("Date of Creation") && !filters.get("Date of Creation").isEmpty()) {
                        LocalDateTime now = LocalDateTime.now();
                        switch (filters.get("Date of Creation")) {
                            case "day":
                                matches = matches && comment.getDateOfCreation().isAfter(now.minusDays(1));
                                break;
                            case "week":
                                matches = matches && comment.getDateOfCreation().isAfter(now.minusWeeks(1));
                                break;
                            case "month":
                                matches = matches && comment.getDateOfCreation().isAfter(now.minusMonths(1));
                                break;
                            case "year":
                                matches = matches && comment.getDateOfCreation().isAfter(now.minusYears(1));
                                break;
                            case "custom":
                                LocalDate startDate = LocalDate.parse(filters.get("Date of Creation_start"));
                                LocalDate endDate = LocalDate.parse(filters.get("Date of Creation_end"));
                                matches = matches && (comment.getDateOfCreation().toLocalDate().isAfter(startDate) || comment.getDateOfCreation().toLocalDate().isEqual(startDate))
                                        && (comment.getDateOfCreation().toLocalDate().isBefore(endDate) || comment.getDateOfCreation().toLocalDate().isEqual(endDate));
                                break;
                        }
                    }
                    return matches;
                })
                .collect(Collectors.toList());
    }
}
