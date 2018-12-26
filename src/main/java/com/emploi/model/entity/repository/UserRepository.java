package com.emploi.model.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emploi.model.entity.User;

@Repository
public abstract interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM user u WHERE u.username = :username")
	public abstract Optional<User> findUserByUsername(@Param("username") String paramString);
}