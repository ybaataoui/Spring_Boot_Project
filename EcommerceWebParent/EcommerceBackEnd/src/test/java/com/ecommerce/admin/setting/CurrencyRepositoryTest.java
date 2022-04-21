package com.ecommerce.admin.setting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.common.entity.Currency;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CurrencyRepositoryTest {
	
	@Autowired CurrencyRepository repo;
	
	@Test
	public void testCreateCurrencies() {
		List<Currency> listCurrencies = Arrays.asList(
			new Currency("United States Dollar", "$", "USD"),
			new Currency("British Pound", "Â£", "GPB"),
			new Currency("Japanese Yen", "Â¥", "JPY"),
			new Currency("Euro", "â‚¬", "EUR"),
			new Currency("Russian Ruble", "â‚½", "RUB"),
			new Currency("South Korean Won", "â‚©", "KRW"),
			new Currency("Chinese Yuan", "Â¥", "CNY"),
			new Currency("Brazilian Real", "R$", "BRL"),
			new Currency("Australian Dollar", "$", "AUD"),
			new Currency("Canadian Dollar", "$", "CAD"),
			new Currency("Vietnamese Ä‘á»“ng ", "â‚«", "VND"),
			new Currency("Indian Rupee", "â‚¹", "INR")
		);
		
		repo.saveAll(listCurrencies);
		
		Iterable<Currency> iterable = repo.findAll();
		
		assertThat(iterable).size().isEqualTo(12);
	}
	

	
	@Test
	public void testUpdateCurrency() {
		
		Integer currencyId = 1;
		String name = "United States Dollar";
		String code = "USD";
		
		Currency currency = repo.findById(currencyId).get();
		
		currency.setName(name);
		currency.setCode(code);
		
		Currency updatedCurrency = repo.save(currency);
		
		assertThat(updatedCurrency.getName()).isEqualTo(name);
		assertThat(updatedCurrency.getCode()).isEqualTo(code);
		
	}
	
	@Test
	public void testListAllOrderByNameAsc() {
		List<Currency> currencyList = repo.findAllByOrderByNameAsc();
		
		currencyList.forEach(System.out::println);
		
		assertThat(currencyList.size()).isGreaterThan(0);
	}

}
