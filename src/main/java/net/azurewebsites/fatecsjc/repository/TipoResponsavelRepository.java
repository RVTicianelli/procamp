package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.TipoResponsavel;

@Repository
public interface TipoResponsavelRepository extends JpaRepository<TipoResponsavel, Integer> {
	@Transactional(readOnly = true)
	@Query("SELECT tipoResponsavel FROM TipoResponsavel tipoResponsavel WHERE tipoResponsavel.id = :id")
	public TipoResponsavel buscaPorId(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT tipoResponsavel FROM TipoResponsavel tipoResponsavel WHERE lower(tipoResponsavel.descricao) LIKE lower(concat(:descricao,'%'))")
	public List<TipoResponsavel> findBydescricaoContains(String descricao);
	
	@Transactional(readOnly = true)
	public List<TipoResponsavel> findBydescricao(String descricao);
}
