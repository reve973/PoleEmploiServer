package com.emploi.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emploi.model.entity.Candidat;

@Repository
public abstract interface CandidatRepository extends JpaRepository<Candidat, Long>
{
  @Query("SELECT c FROM candidat c, user u WHERE u.username =  :username AND c.user.id = u.id")
  public abstract Candidat findCandidatByUsername(@Param("username") String paramString);
}