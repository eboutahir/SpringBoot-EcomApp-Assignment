package com.ICECream.Application.CHEHBI.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.ICECream.Application.CHEHBI.controller.ArticleController;
import com.ICECream.Application.CHEHBI.domain.Article;
import com.ICECream.Application.CHEHBI.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

class ArticleControllerTest {

    private ArticleController articleController;

    private ArticleService articleServiceMock;

    @BeforeEach
    void setUp() {
        // Initialiser les objets nécessaires avant chaque test
        articleServiceMock = mock(ArticleService.class);
        articleController = new ArticleController(articleServiceMock);
    }

    @Test
    void testAddArticle() {
        Model modelMock = mock(Model.class);
        String viewName = articleController.addArticle(modelMock);

        assertEquals("addArticle", viewName);
        assertTrue(modelMock.containsAttribute("article"));
        assertTrue(modelMock.containsAttribute("allSizes"));
        assertTrue(modelMock.containsAttribute("allBrands"));
        assertTrue(modelMock.containsAttribute("allCategories"));

        verifyZeroInteractions(articleServiceMock); // Vérifie qu'aucune interaction directe avec le service n'a eu lieu
    }

    @Test
    void testAddArticlePost() {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        Article article = new Article();
        article.setTitle("Test Article");
        article.setStock(10);
        article.setPrice(29.99);
        article.setPicture("test.jpg");

        when(requestMock.getParameter("size")).thenReturn("S, M");
        when(requestMock.getParameter("category")).thenReturn("Category1, Category2");
        when(requestMock.getParameter("brand")).thenReturn("Brand1, Brand2");

        String viewName = articleController.addArticlePost(article, requestMock);

        assertEquals("redirect:article-list", viewName);

        verify(articleServiceMock).saveArticle(any(Article.class)); // Vérifie que la méthode saveArticle a été appelée avec n'importe quel objet Article
    }

    // Ajoutez d'autres tests pour les méthodes restantes du contrôleur ArticleController
    // ...

}
