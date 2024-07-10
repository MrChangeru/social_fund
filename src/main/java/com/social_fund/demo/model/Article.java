package com.social_fund.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Long authorId;

    private LocalDateTime dateOfCreation;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public Article(Long id, String title, String content, Long authorId, LocalDateTime dateOfCreation) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.dateOfCreation = dateOfCreation;
    }
}
