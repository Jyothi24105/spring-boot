package com.rest.demo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.demo.exception.UserNotFoundException;
import com.rest.demo.model.Post;
import com.rest.demo.model.User;
import com.rest.demo.repository.PostRepository;
import com.rest.demo.repository.UserRepository;

/**
 * 
 * @author r3demo
 * Demo Spring Boot Rest Controller to demonstrate the CRUD operations 
 * for User and Post tables in the Spring Boot Starter H2 database.
 *
 */

@RestController
public class UserJPAResource  {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user =  userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}	

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		 userRepository.delete(id);
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.saveAndFlush(user);
		
		URI location =ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id){
		Optional<User> userOptional =userRepository.findById(id);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id-"+id);
		return userOptional.get().getPosts();
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post){
		Optional<User> userOptional =userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		postRepository.save(post);
		
		URI location =ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
				
		return ResponseEntity.created(location).build();
	}	
}
