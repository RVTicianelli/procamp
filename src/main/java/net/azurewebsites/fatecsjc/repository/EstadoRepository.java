package net.azurewebsites.fatecsjc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
	@Transactional(readOnly = true)
	@Query("SELECT estado FROM Estado estado WHERE estado.id = :id")
	public Estado buscaPorId(@Param("id") Integer id);

	@Nullable
	@Transactional(readOnly = true)
	public Estado findByuf(String uf);

	@Transactional(readOnly = true)
	public Estado findBynomeContains(String nome);

	@Transactional(readOnly = true)
	@Nullable
	@Query("SELECT estado FROM Estado estado WHERE estado.uf = :uf")
	public Estado findByUfAux(@Param("uf") String uf);
}
