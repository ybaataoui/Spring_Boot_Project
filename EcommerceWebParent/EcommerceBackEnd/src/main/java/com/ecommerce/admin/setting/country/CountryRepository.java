package com.ecommerce.admin.setting.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.common.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	public List<Country> findAllByOrderByNameAsc();

}
