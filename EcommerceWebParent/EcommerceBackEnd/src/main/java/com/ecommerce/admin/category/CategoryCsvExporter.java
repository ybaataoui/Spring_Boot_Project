package com.ecommerce.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


//
//import com.ecommerce.admin.AbstractExporter;
//import com.ecommerce.common.entity.Category;
//
//public class CategoryCsvExporter extends AbstractExporter {
//	public void export(List<Category> listCategories, HttpServletResponse response) 
//			throws IOException {
//		super.setResponseHeader(response, "text/csv", ".csv", "categories_");
//		
//		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), 
//				CsvPreference.STANDARD_PREFERENCE);
//		
//		String[] csvHeader = {"Category ID", "Category Name"};
//		String[] fieldMapping = {"id", "name"};
//		
//		csvWriter.writeHeader(csvHeader);
//		
//		for (Category category : listCategories) {
//			category.setName(category.getName().replace("--", "  "));
//			csvWriter.write(category, fieldMapping);
//		}
//		
//		csvWriter.close();
//	}
//}
