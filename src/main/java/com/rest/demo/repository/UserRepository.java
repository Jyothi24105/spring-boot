package com.rest.demo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.model.User;

/**
 * @author r3demo
 * Spring Data Respository for the H2 User table
 *
 */

public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findById(Integer id);
}
