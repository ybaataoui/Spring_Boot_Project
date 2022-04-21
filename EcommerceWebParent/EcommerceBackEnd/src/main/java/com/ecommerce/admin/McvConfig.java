package com.ecommerce.admin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecommerce.admin.paging.PagingAndSortingArgumentResolver;

@Configuration
public class McvConfig implements WebMvcConfigurer {

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		exposeDirectory("user-photos", registry);
//		exposeDirectory("../product-images", registry);
//		exposeDirectory("../category-images", registry);
//		exposeDirectory("../site-logo", registry);
//		
//	}
//	
//	private void exposeDirectory(String pathPathern, ResourceHandlerRegistry registry) {
//		Path path = Paths.get(pathPathern);
//		String absolutePath = path.toFile().getAbsolutePath();
//		
//		String logicalPath = pathPathern.replace("../", "") + "/**";
//		
//		registry.addResourceHandler(logicalPath)
//			.addResourceLocations("file:/" + absolutePath + "/");
//		
//	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new PagingAndSortingArgumentResolver());
	}

	
}
