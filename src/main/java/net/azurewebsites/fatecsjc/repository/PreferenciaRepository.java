package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Preferencia;

@Repository
public interface PreferenciaRepository extends JpaRepository<Preferencia, Integer>{
	@Transactional(readOnly = true)
	@Query("SELECT preferencia FROM Preferencia preferencia WHERE preferencia.id = :id")
	public Preferencia buscaPorId(@Param("id") Integer id);

	@Transactional(readOnly = true)
	public List<Preferencia> findBydescricaoContains(String nome);
	
	@Transactional(readOnly = true)
	@Query("SELECT preferencia FROM Preferencia preferencia WHERE lower(preferencia.nome) LIKE lower(concat(:nome,'%')) ORDER BY preferencia.nome")
	public List<Preferencia> findByNome(String nome);
	
	@Transactional(readOnly = true)
	@Query("SELECT preferencia FROM Preferencia preferencia WHERE preferencia.id in (:ids)")
	public List<Preferencia> findByArrayId(@Param("ids") List<Integer> ids);
}
