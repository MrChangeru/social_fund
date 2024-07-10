package com.social_fund.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private Long userId;

    private String content;

    private LocalDateTime dateOfCreation;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public Comment(Long id, Long articleId, Long userId, String content, LocalDateTime dateOfCreation) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
        this.dateOfCreation = dateOfCreation;
    }
}
