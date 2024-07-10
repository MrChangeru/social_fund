package com.social_fund.demo.service;

import com.social_fund.demo.model.User;
import com.social_fund.demo.model.Article;
import com.social_fund.demo.model.Comment;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

    private final UserService userService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @Autowired
    public ExcelExportService(UserService userService, ArticleService articleService, CommentService commentService) {
        this.userService = userService;
        this.articleService = articleService;
        this.commentService = commentService;
    }

    public void exportUsers(HttpServletResponse response) throws IOException {
        List<User> users = userService.findAllUsers();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");
        createHeaderRow(sheet, new String[]{"ID", "Login", "Last Name", "First Name", "Patronymic", "Date of Birth", "Email", "Date of Creation"});

        int rowCount = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowCount++);
            writeUser(user, row);
        }
        writeWorkbookToResponse(workbook, response);
    }

    public void exportArticles(HttpServletResponse response) throws IOException {
        List<Article> articles = articleService.findAllArticles();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Articles");
        createHeaderRow(sheet, new String[]{"ID", "Title", "Content", "Author ID", "Date of Creation"});
        int rowCount = 1;
        for (Article article : articles) {
            Row row = sheet.createRow(rowCount++);
            writeArticle(article, row);
        }
        writeWorkbookToResponse(workbook, response);
    }

    public void exportComments(HttpServletResponse response) throws IOException {
        List<Comment> comments = commentService.findAllComments();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Comments");
        createHeaderRow(sheet, new String[]{"ID", "Article ID", "User ID", "Content", "Date of Creation"});
        int rowCount = 1;
        for (Comment comment : comments) {
            Row row = sheet.createRow(rowCount++);
            writeComment(comment, row);
        }
        writeWorkbookToResponse(workbook, response);
    }

    private void createHeaderRow(Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    private void writeUser(User user, Row row) {
        row.createCell(0).setCellValue(user.getId());
        row.createCell(1).setCellValue(user.getLogin());
        row.createCell(2).setCellValue(user.getLastName());
        row.createCell(3).setCellValue(user.getFirstName());
        row.createCell(4).setCellValue(user.getPatronymic());
        row.createCell(5).setCellValue(user.getDateOfBirth().toString());
        row.createCell(6).setCellValue(user.getEmail());
        row.createCell(7).setCellValue(user.getDateOfCreation().toString());
    }

    private void writeArticle(Article article, Row row) {
        row.createCell(0).setCellValue(article.getId());
        row.createCell(1).setCellValue(article.getTitle());
        row.createCell(2).setCellValue(article.getContent());
        row.createCell(3).setCellValue(article.getAuthorId());
        row.createCell(4).setCellValue(article.getDateOfCreation().toString());
    }

    private void writeComment(Comment comment, Row row) {
        row.createCell(0).setCellValue(comment.getId());
        row.createCell(1).setCellValue(comment.getArticleId());
        row.createCell(2).setCellValue(comment.getUserId());
        row.createCell(3).setCellValue(comment.getContent());
        row.createCell(4).setCellValue(comment.getDateOfCreation().toString());
    }

    private void writeWorkbookToResponse(Workbook workbook, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
