package com.ecommerce.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.admin.user.UserRepository;
import com.ecommerce.common.entity.Category;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

	@Autowired 
	private CategoryRepository repo;
	
	@Test
	public void testCreateRootCategory() {
		Category bottoms = new Category("Bottoms");
		Category jacketsCoats = new Category("Jackets & Coats");
		
		repo.saveAll(List.of(bottoms, jacketsCoats));
		
		//assertThat(savedCategory.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateSubCategory() {
		Category parent = new Category(3);
		Category shorts = new Category("Jackets", parent);
		Category jeans = new Category("Coats", parent);
		
		repo.saveAll(List.of(shorts, jeans));
		
	}
	
	@Test
	public void testGetCategory() {
		Category category = repo.findById(2).get();
		System.out.println(category.getName());
		
		Set<Category> children = category.getChildren();
		
		for (Category subCategory : children) {
			System.out.println(subCategory.getName());
		}
		
		assertThat(children.size()).isGreaterThan(0);
	}
	
	@Test
	public void testPrintHierarchicalCategories() {
		Iterable<Category> categories = repo.findAll();
		
		for (Category category : categories) {
			if (category.getParent() == null) {
				System.out.println(category.getName());
			}
			
			Set<Category> children = category.getChildren();
			
			for (Category subCategory : children) {
				System.out.println("--" + subCategory.getName());
			}
		}
	}
}
