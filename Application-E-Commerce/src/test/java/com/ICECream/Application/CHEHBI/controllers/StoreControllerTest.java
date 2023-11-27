package com.ICECream.Application.CHEHBI.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ICECream.Application.CHEHBI.controller.StoreController;
import com.ICECream.Application.CHEHBI.domain.Article;
import com.ICECream.Application.CHEHBI.form.ArticleFilterForm;
import com.ICECream.Application.CHEHBI.service.ArticleService;
import com.ICECream.Application.CHEHBI.type.SortFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

class StoreControllerTest {

    private StoreController storeController;

    private ArticleService articleServiceMock;

    @BeforeEach
    void setUp() {
        // Initialiser les objets nécessaires avant chaque test
        articleServiceMock = mock(ArticleService.class);
        storeController = new StoreController(articleServiceMock);
    }

    @Test
    void testStore() {
        ArticleFilterForm filters = new ArticleFilterForm();
        Model modelMock = mock(Model.class);

        String viewName = storeController.store(filters, modelMock);

        assertEquals("store", viewName);
        assertTrue(modelMock.containsAttribute("allCategories"));
        assertTrue(modelMock.containsAttribute("allBrands"));
        assertTrue(modelMock.containsAttribute("allSizes"));
        assertTrue(modelMock.containsAttribute("articles"));
        assertTrue(modelMock.containsAttribute("totalitems"));
        assertTrue(modelMock.containsAttribute("itemsperpage"));

        verify(articleServiceMock).getAllCategories();
        verify(articleServiceMock).getAllBrands();
        verify(articleServiceMock).getAllSizes();
        verify(articleServiceMock).findArticlesByCriteria(any(), anyDouble(), anyDouble(), any(), any(), any(), any());

        // Ajoutez des assertions supplémentaires en fonction de la logique de votre contrôleur
    }

    @Test
    void testArticleDetail() {
        Long articleId = 1L;
        Model modelMock = mock(Model.class);

        String viewName = storeController.articleDetail(articleId, modelMock);

        assertEquals("articleDetail", viewName);
        assertTrue(modelMock.containsAttribute("article"));
        assertTrue(modelMock.containsAttribute("notEnoughStock"));
        assertTrue(modelMock.containsAttribute("addArticleSuccess"));

        verify(articleServiceMock).findArticleById(articleId);

        // Ajoutez des assertions supplémentaires en fonction de la logique de votre contrôleur
    }

    // Ajoutez d'autres tests pour les méthodes restantes du contrôleur StoreController
    // ...

}
