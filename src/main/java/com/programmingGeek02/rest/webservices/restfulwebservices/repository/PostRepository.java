package com.programmingGeek02.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmingGeek02.rest.webservices.restfulwebservices.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> { }
