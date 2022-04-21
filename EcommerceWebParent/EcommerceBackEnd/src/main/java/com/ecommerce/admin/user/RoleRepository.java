package com.ecommerce.admin.user;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	Role findByName(String name);

}
