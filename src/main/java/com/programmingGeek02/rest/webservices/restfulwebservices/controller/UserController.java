package com.programmingGeek02.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.programmingGeek02.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
import com.programmingGeek02.rest.webservices.restfulwebservices.model.User;
import com.programmingGeek02.rest.webservices.restfulwebservices.service.UserDaoService;

import jakarta.validation.Valid;

@RestController
public class UserController
{

	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return service.findAll();
	}
	
	// WebMvcLinkBuilder
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int id)
	{
		User user = service.findById(id);
		
		if(user==null){ throw new UserNotFoundException("id" + id); }
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		// HateoAs
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteOneUser(@PathVariable int id)
	{
		service.deleteById(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createdUser(@Valid @RequestBody User user)
	{
		User savedUser = service.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // From the URl of the current request, add a path and then, replace that variable with the userID
							.path("/{id}")								// And convert it as a URI
							.buildAndExpand(savedUser.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
