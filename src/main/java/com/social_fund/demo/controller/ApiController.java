package com.social_fund.demo.controller;

import com.social_fund.demo.model.User;
import com.social_fund.demo.model.Article;
import com.social_fund.demo.model.Comment;
import com.social_fund.demo.service.UserService;
import com.social_fund.demo.service.ArticleService;
import com.social_fund.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @Autowired
    public ApiController(UserService userService, ArticleService articleService, CommentService commentService) {
        this.userService = userService;
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam Map<String, String> filters) {
        return userService.findUsersWithFilters(filters);
    }

    @GetMapping("/articles")
    public List<Article> getArticles(@RequestParam Map<String, String> filters) {
        return articleService.findArticlesWithFilters(filters);
    }

    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam Map<String, String> filters) {
        return commentService.findCommentsWithFilters(filters);
    }
}
