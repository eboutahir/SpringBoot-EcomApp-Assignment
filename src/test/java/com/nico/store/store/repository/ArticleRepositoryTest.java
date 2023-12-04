package com.nico.store.store.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.nico.store.store.domain.Article;
import com.nico.store.store.domain.ArticleBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	 
	@Autowired
	private ArticleRepository repository;
		 	
	@Before
	public void setUp() {
		Article article = new ArticleBuilder()
				.withTitle("article1")
				.withPrice(50)
				.sizesAvailable(Arrays.asList("38", "39"))
				.ofCategories(Arrays.asList("Fagous"))
				.build();
		entityManager.persist(article);

		Article article2 = new ArticleBuilder()
				.withTitle("article2")
				.withPrice(100)
				.sizesAvailable(Arrays.asList("39", "40"))
				.ofCategories(Arrays.asList("Al Majhoul"))
				.build();
		entityManager.persist(article2);

	}


	
	@Test
	public void should_find_all_distinct_categories() {		
        assertThat(repository.findAllCategories()).hasSize(3).contains("Fagous", "Al Majhoul");
	}
	
	@Test
	public void should_filter_articles_between_prices() {
		int low = 100;
		int high = 300;
		List<Article> results = repository.findAll(ArticleSpecification.filterBy(low, high, null, null, null, null));
		assertThat(results).hasSize(3).extracting("title").contains("article2");
	}
	

	
	@Test
	public void should_filter_articles_with_price_lower_than_number() {
		int high = 200;
		List<Article> results = repository.findAll(ArticleSpecification.filterBy(null, high, null, null, null, null));
		assertThat(results).hasSize(3).extracting("title").contains("article1", "article2");
	}
	

	
	@Test
	public void should_filter_articles_by_category() {
		List<Article> results = repository.findAll(ArticleSpecification.filterBy(null, null, null, Arrays.asList("Al Majhoul", "Fagous"), null, null));
		assertThat(results).hasSize(2).extracting("title").contains("article1", "article2");
	}
	

	@Test
	public void should_filter_articles_by_search_term() {
		List<Article> results = repository.findAll(ArticleSpecification.filterBy(null, null, null, null, null, "article"));
		List<Article> results2 = repository.findAll(ArticleSpecification.filterBy(null, null, null, null, null, "cle4"));
		List<Article> results3 = repository.findAll(ArticleSpecification.filterBy(null, null, null, null, null, "unmatchingterm"));
		assertThat(results).hasSize(5).extracting("title").contains("article1", "article2", "article3", "article4", "article5");
		assertThat(results2).hasSize(1).extracting("title").contains("article4");
		assertThat(results3).isEmpty();
	}
	
	@Test
	public void should_find_all_if_all_filters_are_null() {
		List<Article> results = repository.findAll(ArticleSpecification.filterBy(null, null, null, null, null, null));
		assertThat(results).hasSize(5).extracting("title").contains("article1", "article2");
	}

}
