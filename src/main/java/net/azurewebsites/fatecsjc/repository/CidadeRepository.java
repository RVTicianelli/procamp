package net.azurewebsites.fatecsjc.repository;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Cidade;
import net.azurewebsites.fatecsjc.entity.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	@Transactional(readOnly = true)
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.id = :idBusca")
	public Cidade buscaPorId(@Param("idBusca")Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.estado.id = :estadoId AND lower(cidade.nome) LIKE lower(concat(:cidadeNome,'%'))")
	public LinkedList<Cidade> findByNomeContainsAndUf(@Param("estadoId") Integer estadoId, @Param("cidadeNome") String cidadeNome);
	
	@Transactional(readOnly = true)
	public Cidade findBynome(String nome);
	
	@Transactional(readOnly = true)
	public LinkedList<Cidade> findByEstado(Estado estado);
	
	@Transactional(readOnly = true)
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.estado.id = :estadoId AND cidade.nome like :cidadeNome")
	public LinkedList<Cidade> findCidadesEstadoIdNomeCidades(@Param("estadoId")Integer estadoId, @Param("cidadeNome")String cidadeNome);
	
	@Transactional(readOnly = true)
	@Nullable
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.estado.id = :estadoId AND cidade.nome like :cidadeNome")
	public Cidade findCidadesEstadoIdNomeCidadesAux(@Nullable@Param("estadoId")Integer estadoId, @Nullable@Param("cidadeNome")String cidadeNome);
	
}
