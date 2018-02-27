package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.demo.model.Post;

/**
 * @author r3demo
 * Spring Data Respository JPA for the H2 Post table
 *
 */

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
	
}
