package com.emploi.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emploi.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}