package com.programmingGeek02.rest.webservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.programmingGeek02.rest.webservices.restfulwebservices.model.Post;
import com.programmingGeek02.rest.webservices.restfulwebservices.model.User;
import com.programmingGeek02.rest.webservices.restfulwebservices.repository.PostRepository;
import com.programmingGeek02.rest.webservices.restfulwebservices.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaController
{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int id)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()){ throw new UserNotFoundException("id" + id); }
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteOneUser(@PathVariable int id)
	{
		repository.deleteById(id);
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createdUser(@Valid @RequestBody User user)
	{
		User savedUser = repository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // From the URl of the current request, add a path and then, replace that variable with the userID
							.path("/{id}")								// And convert it as a URI
							.buildAndExpand(savedUser.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()){ throw new UserNotFoundException("id" + id); }
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // From the URl of the current request, add a path and then, replace that variable with the userID
				.path("/{id}")								// And convert it as a URI
				.buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()){ throw new UserNotFoundException("id" + id); }
		
		return user.get().getPosts();
		
	}
	
	@GetMapping("/jpa/users/{id}/posts/{PostId}")
	public Post retrieveOnePostForUser(@PathVariable int id, @PathVariable int PostId)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()){ throw new UserNotFoundException("id" + id); }
		
		return user.get().getPosts().get(PostId);
		
	}
	
}
