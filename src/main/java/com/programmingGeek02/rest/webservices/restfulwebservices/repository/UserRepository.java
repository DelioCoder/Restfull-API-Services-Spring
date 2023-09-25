package com.programmingGeek02.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmingGeek02.rest.webservices.restfulwebservices.model.User;

public interface UserRepository extends JpaRepository<User, Integer> { }
