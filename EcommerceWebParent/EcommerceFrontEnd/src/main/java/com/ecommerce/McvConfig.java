package com.ecommerce;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class McvConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("../category-images", registry);
		exposeDirectory("../product-images", registry);
		exposeDirectory("../site-logo", registry);
		
	}
	
	private void exposeDirectory(String pathPathern, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPathern);
		String absolutePath = path.toFile().getAbsolutePath();
		
		String logicalPath = pathPathern.replace("../", "") + "/**";
		
		registry.addResourceHandler(logicalPath)
			.addResourceLocations("file:/" + absolutePath + "/");
		
	}

	
}
