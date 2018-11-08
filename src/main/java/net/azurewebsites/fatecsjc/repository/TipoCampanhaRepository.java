package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.TipoCampanha;

@Repository
public interface TipoCampanhaRepository extends JpaRepository<TipoCampanha, Integer>{
	@Transactional(readOnly = true)
	@Query("SELECT tipoCampanha FROM TipoCampanha tipoCampanha WHERE tipoCampanha.id = :id")
	public TipoCampanha buscaPorId(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT tipoCampanha FROM TipoCampanha tipoCampanha WHERE lower(tipoCampanha.descricao) LIKE lower(concat(:descricao,'%'))")
	public List<TipoCampanha> findBydescricaoContains(String descricao);
	
	@Transactional(readOnly = true)
	public List<TipoCampanha> findBydescricao(String descricao);
}
