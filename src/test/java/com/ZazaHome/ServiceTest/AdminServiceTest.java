package com.ZazaHome.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.Zazahome.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import com.Zazahome.daos.CategoryRepository;
import com.Zazahome.daos.ProductRepository;
import com.Zazahome.daos.UserRepository;
import com.Zazahome.entities.Category;
import com.Zazahome.entities.Product;
import com.Zazahome.entities.User;

public class AdminServiceTest {

    private AdminService adminService;
    private UserRepository userRepositoryMock;
    private CategoryRepository categoryRepositoryMock;
    private ProductRepository productRepositoryMock;
    private HttpSession httpSessionMock;

    @BeforeEach
    public void setUp() {
        userRepositoryMock = mock(UserRepository.class);
        categoryRepositoryMock = mock(CategoryRepository.class);
        productRepositoryMock = mock(ProductRepository.class);
        httpSessionMock = mock(HttpSession.class);

        adminService = new AdminService();
        adminService.setUserRepo(userRepositoryMock);
        adminService.setCategoryRepo(categoryRepositoryMock);
        adminService.setProductRepo(productRepositoryMock);
    }

    @Test
    public void testLoadUserByUserName() {
        // Arrange
        String username = "testUser";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        when(userRepositoryMock.loadUserByUserName(username)).thenReturn(expectedUser);

        // Act
        User actualUser = adminService.loadUserByUserName(username);

        // Assert
        assertNotNull(actualUser);
        assertEquals(username, actualUser.getUsername());
        verify(userRepositoryMock, times(1)).loadUserByUserName(username);
    }

    @Test
    public void testGetUsers() {
        // Arrange
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        when(userRepositoryMock.getUsers()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = adminService.getUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals(expectedUsers.size(), actualUsers.size());
        verify(userRepositoryMock, times(1)).getUsers();
    }

    @Test
    public void testGetCategories() {
        // Arrange
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category());
        expectedCategories.add(new Category());
        when(categoryRepositoryMock.getCategories()).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = adminService.getCategories();

        // Assert
        assertNotNull(actualCategories);
        assertEquals(expectedCategories.size(), actualCategories.size());
        verify(categoryRepositoryMock, times(1)).getCategories();
    }

    @Test
    public void testGetProducts() {
        // Arrange
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        when(productRepositoryMock.getProducts()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = adminService.getProducts();

        // Assert
        assertNotNull(actualProducts);
        assertEquals(expectedProducts.size(), actualProducts.size());
        verify(productRepositoryMock, times(1)).getProducts();
    }

    @Test
    public void testHandleActionSuspend() throws IOException {
        // Arrange
        Integer userId = 1;
        String type = "suspend";
        User user = new User();
        user.setId(userId);
        when(userRepositoryMock.getById(userId)).thenReturn(user);

        // Act
        String result = adminService.handleAction(userId, type, null, null, httpSessionMock);

        // Assert
        assertEquals("userSuspendById=" + userId, result);
        assertFalse(user.isEnable());
        verify(userRepositoryMock, times(1)).save(user);
        verify(httpSessionMock, times(1)).setAttribute("status", "suspended");
    }

    @Test
    public void testHandleActionUnsuspend() throws IOException {
        // Arrange
        Integer userId = 2;
        String type = "unsuspend";
        User user = new User();
        user.setId(userId);
        user.setEnable(false);
        when(userRepositoryMock.getById(userId)).thenReturn(user);

        // Act
        String result = adminService.handleAction(userId, type, null, null, httpSessionMock);

        // Assert
        assertEquals("userUnsuspendById=" + userId, result);
        assertTrue(user.isEnable());
        verify(userRepositoryMock, times(1)).save(user);
        verify(httpSessionMock, times(1)).setAttribute("status", "unsuspended");
    }

    @Test
    public void testHandleActionDelete() throws IOException {
        // Arrange
        Integer userId = 3;
        String type = "delete";
        User user = new User();
        user.setId(userId);
        when(userRepositoryMock.getById(userId)).thenReturn(user);

        // Act
        String result = adminService.handleAction(userId, type, null, null, httpSessionMock);

        // Assert
        assertEquals("userDeleteById=" + userId, result);
        verify(userRepositoryMock, times(1)).delete(user);
        verify(httpSessionMock, times(1)).setAttribute("status", "user-deleted");
    }

    @Test
    public void testHandleActionAddNewCategoryWithTitleNull() throws IOException {
        // Arrange
        String categoryAction = "addNew";
        String categoryTitle = null;

        // Act
        String result = adminService.handleAction(null, null, categoryAction, categoryTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "title-null");
    }

    @Test
    public void testHandleActionAddNewCategoryCategoryAlreadyExists() throws IOException {
        // Arrange
        String categoryAction = "addNew";
        String categoryTitle = "ExistingCategory";
        doThrow(DataIntegrityViolationException.class).when(categoryRepositoryMock).save(any(Category.class));

        // Act
        String result = adminService.handleAction(null, null, categoryAction, categoryTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "category-already-exist");
    }

    @Test
    public void testHandleActionAddNewCategoryCategoryValid() throws IOException {
        // Arrange
        String categoryAction = "addNew";
        String categoryTitle = "NewCategory";
        Category savedCategory = new Category();
        when(categoryRepositoryMock.save(any(Category.class))).thenReturn(savedCategory);

        // Act
        String result = adminService.handleAction(null, null, categoryAction, categoryTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "category-added");
        verify(categoryRepositoryMock, times(1)).save(any(Category.class));
    }

    @Test
    public void testHandleActionAddNewCategoryCategoryBlank() throws IOException {
        // Arrange
        String categoryAction = "addNew";
        String categoryTitle = "";

        // Act
        String result = adminService.handleAction(null, null, categoryAction, categoryTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "title-null");
    }

    @Test
    public void testHandleActionAddNewProductWithTitleNull() throws IOException {
        // Arrange
        String productAction = "addNew";
        String productTitle = null;

        // Act
        String result = adminService.handleAction(null, null, productAction, productTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "title-null");
    }

    @Test
    public void testHandleActionAddNewProductProductAlreadyExists() throws IOException {
        // Arrange
        String productAction = "addNew";
        String productTitle = "ExistingProduct";
        doThrow(DataIntegrityViolationException.class).when(productRepositoryMock).save(any(Product.class));

        // Act
        String result = adminService.handleAction(null, null, productAction, productTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "product-already-exist");
    }

    @Test
    public void testHandleActionAddNewProductProductValid() throws IOException {
        // Arrange
        String productAction = "addNew";
        String productTitle = "NewProduct";
        Product savedProduct = new Product();
        when(productRepositoryMock.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        String result = adminService.handleAction(null, null, productAction, productTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "product-added");
        verify(productRepositoryMock, times(1)).save(any(Product.class));
    }

    @Test
    public void testHandleActionAddNewProductProductBlank() throws IOException {
        // Arrange
        String productAction = "addNew";
        String productTitle = "";

        // Act
        String result = adminService.handleAction(null, null, productAction, productTitle, httpSessionMock);

        // Assert
        assertEquals("", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "title-null");
    }

    @Test
    public void testHandleActionDeleteCategoryWithAssociatedProducts() throws IOException {
        // Arrange
        Integer categoryId = 1;
        String type = "delete";
        Category category = new Category();
        category.setId(categoryId);
        List<Product> associatedProducts = new ArrayList<>();
        associatedProducts.add(new Product());
        associatedProducts.add(new Product());
        when(categoryRepositoryMock.getById(categoryId)).thenReturn(category);
        when(productRepositoryMock.getProductsByCategory(category)).thenReturn(associatedProducts);

        // Act
        String result = adminService.handleAction(categoryId, type, null, null, httpSessionMock);

        // Assert
        assertEquals("categoryDeleteWithAssociatedProducts=" + categoryId, result);
        verify(categoryRepositoryMock, never()).delete(any(Category.class));
        verify(httpSessionMock, times(1)).setAttribute("status", "category-delete-associated-products");
    }
    @Test
    public void testHandleActionUpdateCategory() throws IOException {
        // Arrange
        Integer categoryId = 1;
        String type = "update";
        Category category = new Category();
        category.setId(categoryId);
        when(categoryRepositoryMock.getById(categoryId)).thenReturn(category);

        // Act
        String result = adminService.handleAction(categoryId, type, null, null, httpSessionMock);

        // Assert
        assertEquals("categoryUpdateById=" + categoryId, result);
        verify(httpSessionMock, times(1)).setAttribute("status", "category-updated");
    }

    @Test
    public void testHandleActionUpdateProduct() throws IOException {
        // Arrange
        Integer productId = 1;
        String type = "update";
        Product product = new Product();
        product.setId(productId);
        when(productRepositoryMock.getById(productId)).thenReturn(product);

        // Act
        String result = adminService.handleAction(productId, type, null, null, httpSessionMock);

        // Assert
        assertEquals("productUpdateById=" + productId, result);
        verify(httpSessionMock, times(1)).setAttribute("status", "product-updated");
    }

    @Test
    public void testHandleActionInvalidUpdateType() throws IOException {
        // Arrange
        Integer entityId = 1;
        String invalidType = "update";

        // Act
        String result = adminService.handleAction(entityId, invalidType, null, null, httpSessionMock);

        // Assert
        assertEquals("invalidUpdateActionType", result);
        verify(httpSessionMock, times(1)).setAttribute("status", "invalid-update-action-type");
    }

}