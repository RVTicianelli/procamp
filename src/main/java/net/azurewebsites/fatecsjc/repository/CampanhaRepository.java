package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Responsavel;
import net.azurewebsites.fatecsjc.entity.TipoCampanha;
import net.azurewebsites.fatecsjc.entity.dto.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE campanha.id = :id")
	public Campanha buscaPorId(@Param("id")Integer id);
	
	@Transactional(readOnly = true)
	public List<Campanha> findByResponsavel(Responsavel responsavel);
	
	@Transactional(readOnly = true)
	public List<Campanha> findByNome(String nome);
	
	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE lower(campanha.nome) LIKE lower(concat(:nomeBusca,'%'))")
	public List<Campanha> findByNomeContains(@Param("nomeBusca") String nomeBusca);

	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE campanha.dataInicio >= :data")
	public List<Campanha> buscaPorDataInicial(@Param("data")String data);
	
	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE campanha.dataInicio <= :data")
	public List<Campanha> buscaPorDataFinal(@Param("data")String data);
	
	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE campanha.dataInicio >= :dataInicio AND campanha.dataFim <= :dataFim")
	public List<Campanha> buscaPorPeriodo(@Param("dataInicio")String dataInicio, @Param("dataFim")String dataFim);
	
	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE campanha.dataInicio <= :data AND campanha.dataFim >= :data")
	public List<Campanha> buscaVigentes(@Param("data")String data);
	
	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE campanha.dataCadastro >= :data")
	public List<Campanha> buscaNovas(@Param("data")String data);
	
	@Transactional(readOnly = true)
	@Query("SELECT campanha FROM Campanha campanha WHERE campanha.tipoCampanha.id = :tipoCampanhaId AND campanha.dataCadastro >= :data")
	public List<Campanha> buscaNovasPorTipo(@Param("tipoCampanhaId")Integer tipoCampanhaId, @Param("data")String data);
	
	@Transactional(readOnly = true)
	public List<Campanha> findByPreferencias_id(Integer id);
	
	@Transactional(readOnly = true)
	public List<Campanha> findByTipoCampanha(TipoCampanha tipoCampanha);
	
}
