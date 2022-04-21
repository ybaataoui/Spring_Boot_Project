package com.ecommerce.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.common.entity.Role;
import com.ecommerce.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired 
	private UserRepository repo;
	
	@Autowired 
	private TestEntityManager entityManager; // Jpa class test to interact with database. for example get a role from roles table.
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userYoussef = new User("youssef@yahoo.com", "202020", "Youssef", "Baataoui");
		userYoussef.addRole(roleAdmin);
		
		User savedRole = repo.save(userYoussef);
		assertThat(savedRole.getId()).isGreaterThan(0);	
	}
	
	@Test
	public void testCreateNewUserWithTwoRole() {
		User userJoud = new User("joud@yahoo.com", "202020", "Joud", "Baataoui");
		
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userJoud.addRole(roleEditor);
		userJoud.addRole(roleAssistant);
		
		User savedUser = repo.save(userJoud);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> usersList = repo.findAll();
		
		usersList.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUSerById() {
		User user = repo.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testUpdateUserdetails() {
		User user = repo.findById(1).get();
		user.setEmail("baataoui@gmail.com");
		user.setEnabled(true);
		
		repo.save(user);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User user = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		
		user.getRoles().remove(roleEditor);
		user.addRole(roleSalesperson);
		
		repo.save(user);
	}
	
	@Test
	public void testDeleteUser() {
		User user = repo.findById(2).get();
		
		repo.delete(user);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "yassine@gmail.com";
		
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById() {
		Integer id = 1;
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
		
	}
	
	@Test
	public void testEnabledUser() {
		Integer id = 1;
		repo.updateEnabledStatus(id, true);
	}
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
	
	@Test
	public void testSearchUsers() {
		String keyword = "bruce";
		
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isGreaterThan(0);
	}

}
