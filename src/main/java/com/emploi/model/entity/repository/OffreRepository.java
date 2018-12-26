package com.emploi.model.entity.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emploi.model.entity.Offre;

@Repository
public abstract interface OffreRepository extends JpaRepository<Offre, Long> {
	@Transactional
	@Modifying
	@Query("DELETE FROM offre WHERE id = :id")
	public abstract void deleteById(@Param("id") Long paramLong);

	@Query("SELECT o FROM offre o, contrat c WHERE c.id = o.contrat.id AND (lower(titre) LIKE lower(:keyword) OR lower(description) LIKE lower(:keyword)) AND lower(commune) LIKE lower(:commune) AND c.nom IN (:contrat) AND o.entreprise.id = :id_entreprise")
	public abstract Page<Offre> findPageByEntreprise(@Param("id_entreprise") Long paramLong,
			@Param("keyword") String paramString1, @Param("commune") String paramString2,
			@Param("contrat") List<String> paramList, Pageable paramPageable);

	@Query("SELECT o FROM offre o WHERE o.entreprise.id = :id_entreprise")
	public abstract Page<Offre> findPageForEntrepriseId(@Param("id_entreprise") Long paramLong, Pageable paramPageable);

	@Query("SELECT o FROM offre o")
	public abstract Page<Offre> findPage(Pageable paramPageable);

	@Query("SELECT o FROM offre o, contrat c WHERE c.id = o.contrat.id AND (lower(titre) LIKE lower(:keyword) OR lower(description) LIKE lower(:keyword)) AND lower(commune) LIKE lower(:commune) AND c.nom IN (:contrat)")
	public abstract Page<Offre> findPage(@Param("keyword") String paramString1, @Param("commune") String paramString2,
			@Param("contrat") List<String> paramList, Pageable paramPageable);

	@Query("SELECT o FROM offre o WHERE (lower(titre) LIKE lower(:keyword) OR lower(description) LIKE lower(:keyword))")
	public abstract Page<Offre> findPage(@Param("keyword") String paramString, Pageable paramPageable);
}