package net.javaguides.springboot.service;

import net.javaguides.springboot.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

	List<Article> findAllArticles();
	
	Page<Article> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands, String search);
		
	List<Article> findFirstArticles();

	Article findArticleById(Long id);
	
	Article saveArticle(Article article);

	void deleteArticleById(Long id);
	
	List<String> getAllSizes();

	List<String> getAllCategories();

	List<String> getAllBrands();

}
