package com.internship.internship.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.internship.entity.UsersModel;

public interface UserDao extends JpaRepository<UsersModel,String> {

}
