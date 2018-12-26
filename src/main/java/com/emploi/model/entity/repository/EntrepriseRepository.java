package com.emploi.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emploi.model.entity.Entreprise;

@Repository
public abstract interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
@Query("SELECT e FROM entreprise e, user u WHERE u.username =  :username AND e.user.id = u.id")
public abstract Entreprise findEntrepriseByUsername(@Param("username") String paramString);
}