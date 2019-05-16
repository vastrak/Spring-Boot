package com.vastrak.springboot001.repositories;

import com.vastrak.springboot001.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Christian
 *
 */
@Repository
public abstract interface UserRepository extends JpaRepository<User, Long> {
	public abstract User findByName(String paramString);
}