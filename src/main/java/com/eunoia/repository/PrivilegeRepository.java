package com.eunoia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eunoia.entity.Privilege;
import com.eunoia.entity.User;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
