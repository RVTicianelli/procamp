package net.azurewebsites.fatecsjc.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Responsavel;
import net.azurewebsites.fatecsjc.entity.TipoResponsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer>{
	@Transactional(readOnly = true)
	@Query("SELECT responsavel FROM Responsavel responsavel WHERE responsavel.id = :id")
	public Responsavel buscaPorId(@Param("id")Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT responsavel FROM Responsavel responsavel WHERE responsavel.tipoResponsavel.id = :idTipoResponsavel AND lower(responsavel.nome) LIKE lower(concat(:nome,'%'))")
	public LinkedList<Responsavel> findByNomeContainsAndType(@Param("idTipoResponsavel") Integer idTipoResponsavel, @Param("nome") String nome);
	
	
	@Transactional(readOnly = true)
	public List<Responsavel> findBynome(String nome);
	
	@Transactional(readOnly = true)
	public Responsavel findByCpf(String cpf);

	@Transactional(readOnly = true)
	public Responsavel findByCnpj(String cnpj);
	
	@Transactional(readOnly = true)
	public List<Responsavel> findByRazaoSocial(String razaoSocial);
	
	@Transactional(readOnly = true)
	public List<Responsavel> findByTipoResponsavel(TipoResponsavel tipoResponsavel);
}
