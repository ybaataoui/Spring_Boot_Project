package com.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.common.entity.product.Product;
import com.ecommerce.common.exception.ProductNotFoundException;

@Service
public class ProductService {
	public static final int PRODUCTS_PER_PAGE = 10;
	
	@Autowired private ProductRepository repo;
	
	public  List<Product> getProducts() {
			
		return (List<Product>) repo.findAll();
	}
	
//	public Page<Product> getProducts(int pageNum, String keyword) {
//    	
//    	Pageable pageable = PageRequest.of(pageNum, PRODUCT_PER_PAGE);
//    	
//    	return repo.findAll(pageable);
//    }
	
	public Page<Product> listByCategory(int pageNum, Integer categoryId) {
    	String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";
    	
    	Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
    	
    	return repo.listByCategory(categoryId, categoryIdMatch, pageable);
    }
	
	public Page<Product> listByPage(int pageNum, String keyword, Integer categoryId) {
		//Sort sort = Sort.by(sortField);
		
		//sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
				
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		
		if (keyword != null && !keyword.isEmpty()) {
			if (categoryId != null && categoryId > 0) {
				String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";
				return repo.searchInCategory(categoryId, categoryIdMatch, keyword, pageable);
			}
			
			return repo.findAll(keyword, pageable);
		}
		
		if (categoryId != null && categoryId > 0) {
			String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";
			return repo.findAllInCategory(categoryId, categoryIdMatch, pageable);
		}
		
		return repo.findAll(pageable);		
	}	
	
	public Product getProduct(String alias) throws ProductNotFoundException {
		Product product = repo.findByAlias(alias);
		if (product == null) {
			throw new ProductNotFoundException("Could not find any product with alias " + alias);
		}
		
		return product;
	}
	

}
