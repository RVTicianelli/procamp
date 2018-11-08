package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Cep;
import net.azurewebsites.fatecsjc.entity.Cidade;

public interface CepRepository extends JpaRepository<Cep, Integer>{
	
	@Transactional(readOnly = true)
	@Query("SELECT cep FROM Cep cep WHERE cep.id = :idBusca")
	public Cep buscaPorId(@Param("idBusca")Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT cep FROM Cep cep WHERE cep.cep = :cepBusca")
	public Cep findBycep(@Param("cepBusca") String cepBusca);
	
	@Transactional(readOnly = true)
	@Query("SELECT cep FROM Cep cep WHERE lower(cep.cep) LIKE lower(concat(:cepBusca,'%'))")
	public List<Cep> findByCepList(@Param("cepBusca") String cepBusca);
	
	@Transactional(readOnly = true)
	public List<Cep> findBynomeRua(String nomeRua);
	
	@Transactional(readOnly = true)
	@Query("SELECT cep FROM Cep cep WHERE cep.cidade = :cidadeBusca")
	public List<Cep> findByCidade(@Param("cidadeBusca") Cidade cidade);

}
