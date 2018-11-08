package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.TipoLocalidade;

@Repository
public interface TipoLocalidadeRepository extends JpaRepository<TipoLocalidade, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT tipoLocalidade FROM TipoLocalidade tipoLocalidade WHERE tipoLocalidade.id = :id")
	public TipoLocalidade buscaPorId(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT tipoLocalidade FROM TipoLocalidade tipoLocalidade WHERE lower(tipoLocalidade.descricao) LIKE lower(concat(:descricao,'%'))")
	public List<TipoLocalidade> findBydescricaoContains(String descricao);
	
	@Transactional(readOnly = true)
	public List<TipoLocalidade> findBydescricao(String descricao);
}
