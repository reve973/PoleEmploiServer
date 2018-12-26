package com.emploi.model.entity.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emploi.model.entity.Candidature;
import com.emploi.model.entity.CandidatureId;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, CandidatureId> {
	@Query("SELECT c FROM candidature c WHERE c.id.offre.id = :idOffre AND c.id.candidat.id = :idCandidat")
	public Optional<Candidature> findById(@Param("idOffre") Long idOffre, @Param("idCandidat") Long idCandidat);

	
	@Transactional
	@Modifying
	@Query("DELETE FROM candidature WHERE id.offre.id = :idOffre AND id.candidat.id = :idCandidat")
	public void deleteById(@Param("idOffre") Long idOffre, @Param("idCandidat") Long idCandidat);
}