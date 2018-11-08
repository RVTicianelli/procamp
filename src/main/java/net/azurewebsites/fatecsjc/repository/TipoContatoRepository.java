package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.TipoContato;

@Repository
public interface TipoContatoRepository extends JpaRepository<TipoContato, Integer>{
	@Transactional(readOnly = true)
	@Query("SELECT tipoContato FROM TipoContato tipoContato WHERE tipoContato.id = :id")
	public TipoContato buscaPorId(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT tipoContato FROM TipoContato tipoContato WHERE lower(tipoContato.descricao) LIKE lower(concat(:descricao,'%'))")
	public List<TipoContato> findBydescricaoContains(String descricao);
	
	@Transactional(readOnly = true)
	public List<TipoContato> findBydescricao(String descricao);
}
