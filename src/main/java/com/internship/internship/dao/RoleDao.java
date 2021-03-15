package com.internship.internship.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.internship.entity.UserRole;

public interface RoleDao extends JpaRepository<UserRole,Integer> {
	
}
