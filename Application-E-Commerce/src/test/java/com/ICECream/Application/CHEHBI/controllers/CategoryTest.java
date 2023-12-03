package com.ICECream.Application.CHEHBI.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.ICECream.Application.CHEHBI.domain.Article;
import com.ICECream.Application.CHEHBI.domain.Category;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class CategoryTest {

    @Test
    void testGetId() {
        Category category = new Category();
        assertNull(category.getId());
    }

    @Test
    void testSetId() {
        Category category = new Category();
        category.setId(1L);
        assertEquals(1L, Optional.ofNullable(category.getId()));
    }

    @Test
    void testGetArticle() {
        Article article = new Article();
        Category category = new Category();
        category.setArticle(article);
        assertEquals(article, category.getArticle());
    }

    @Test
    void testSetArticle() {
        Article article = new Article();
        Category category = new Category();
        category.setArticle(article);
        assertEquals(article, category.getArticle());
    }

    @Test
    void testGetName() {
        Category category = new Category();
        category.setName("TestCategory");
        assertEquals("TestCategory", category.getName());
    }

    @Test
    void testSetName() {
        Category category = new Category();
        category.setName("TestCategory");
        assertEquals("TestCategory", category.getName());
    }

    @Test
    void testParameterizedConstructor() {
        Article article = new Article();
        Category category = new Category("TestCategory", article);
        assertEquals("TestCategory", category.getName());
        assertEquals(article, category.getArticle());
    }

    @Test
    void testDefaultConstructor() {
        Category category = new Category();
        assertNull(category.getId());
        assertNull(category.getName());
        assertNull(category.getArticle());
    }

    @Test
    void testEquals() {
        Article article1 = new Article();
        article1.setId(1L);

        Category category1 = new Category("TestCategory", article1);
        category1.setId(1L);

        Category category2 = new Category("TestCategory", article1);
        category2.setId(1L);

        Category category3 = new Category("TestCategory", article1);
        category3.setId(2L);

        assertEquals(category1, category2);
        assertNotEquals(category1, category3);
    }

    @Test
    void testHashCode() {
        Article article1 = new Article();
        article1.setId(1L);

        Category category1 = new Category("TestCategory", article1);
        category1.setId(1L);

        Category category2 = new Category("TestCategory", article1);
        category2.setId(1L);

        Category category3 = new Category("TestCategory", article1);
        category3.setId(2L);

        assertEquals(category1.hashCode(), category2.hashCode());
        assertNotEquals(category1.hashCode(), category3.hashCode());
    }
}
