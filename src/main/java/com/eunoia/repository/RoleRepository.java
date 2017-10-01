package com.eunoia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eunoia.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	  Role findByName(String name);

}
