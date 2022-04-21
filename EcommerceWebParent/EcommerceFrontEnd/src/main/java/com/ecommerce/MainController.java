package com.ecommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.category.CategoryService;
import com.ecommerce.common.entity.Category;
import com.ecommerce.common.entity.product.Product;
import com.ecommerce.product.ProductService;

@Controller
public class MainController {
	
	@Autowired private CategoryService categoryService;
	@Autowired private ProductService productService;
	
	
	@GetMapping("")
	public String listFirstPage(Model model) {
		return listByPage(1, model, null, 0);
	}
	
	@GetMapping("/page/{pageNum}")
	public String listByPage(
			@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("keyword") String keyword,
			@Param("categoryId") Integer categoryId
			) {
		Page<Product> page = productService.listByPage(pageNum, keyword, categoryId);
		List<Product> listProducts = page.getContent();
		
		List<Category> listCategories = categoryService.listNoChildrenCategories();
		
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
		long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		
		if (categoryId != null) model.addAttribute("categoryId", categoryId); 
			
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("keyword", keyword);		
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("listCategories", listCategories);	
		
		
		return "index";		
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		return "redirect:/";
	}	

}
