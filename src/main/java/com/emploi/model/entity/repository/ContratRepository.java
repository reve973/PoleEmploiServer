package com.emploi.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emploi.model.entity.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
}