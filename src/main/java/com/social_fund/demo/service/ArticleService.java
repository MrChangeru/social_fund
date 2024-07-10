package com.social_fund.demo.service;

import com.social_fund.demo.model.Article;
import com.social_fund.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public List<Article> findArticlesWithFilters(Map<String, String> filters) {
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .filter(article -> {
                    boolean matches = true;
                    if (filters.containsKey("ID") && !filters.get("ID").isEmpty()) {
                        matches = matches && article.getId().toString().contains(filters.get("ID"));
                    }
                    if (filters.containsKey("Title") && !filters.get("Title").isEmpty()) {
                        matches = matches && article.getTitle().contains(filters.get("Title"));
                    }
                    if (filters.containsKey("Content") && !filters.get("Content").isEmpty()) {
                        matches = matches && article.getContent().contains(filters.get("Content"));
                    }
                    if (filters.containsKey("Author ID") && !filters.get("Author ID").isEmpty()) {
                        matches = matches && article.getAuthorId().toString().contains(filters.get("Author ID"));
                    }
                    if (filters.containsKey("Date of Creation") && !filters.get("Date of Creation").isEmpty()) {
                        LocalDateTime now = LocalDateTime.now();
                        switch (filters.get("Date of Creation")) {
                            case "day":
                                matches = matches && article.getDateOfCreation().isAfter(now.minusDays(1));
                                break;
                            case "week":
                                matches = matches && article.getDateOfCreation().isAfter(now.minusWeeks(1));
                                break;
                            case "month":
                                matches = matches && article.getDateOfCreation().isAfter(now.minusMonths(1));
                                break;
                            case "year":
                                matches = matches && article.getDateOfCreation().isAfter(now.minusYears(1));
                                break;
                            case "custom":
                                LocalDate startDate = LocalDate.parse(filters.get("Date of Creation_start"));
                                LocalDate endDate = LocalDate.parse(filters.get("Date of Creation_end"));
                                matches = matches && (article.getDateOfCreation().toLocalDate().isAfter(startDate) || article.getDateOfCreation().toLocalDate().isEqual(startDate))
                                        && (article.getDateOfCreation().toLocalDate().isBefore(endDate) || article.getDateOfCreation().toLocalDate().isEqual(endDate));
                                break;
                        }
                    }
                    return matches;
                })
                .collect(Collectors.toList());
    }
}
