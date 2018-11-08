package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Contato;
import net.azurewebsites.fatecsjc.entity.Localidade;
import net.azurewebsites.fatecsjc.entity.Responsavel;
import net.azurewebsites.fatecsjc.entity.TipoContato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer>{
	@Transactional(readOnly = true)
	@Query("SELECT contato FROM Contato contato WHERE contato.id = :id")
	public Contato buscaPorId(@Param("id")Integer id);
	
	@Transactional(readOnly = true)
	public List<Contato> findByContato(String contato);
	
	@Transactional(readOnly = true)
	public List<Contato> findByValor(String contato);

	@Transactional(readOnly = true)
	public List<Contato> findByLocalidade(Localidade localidade);

	@Transactional(readOnly = true)
	public List<Contato> findByResponsavel(Responsavel responsavel);
	
	@Transactional(readOnly = true)
	@Query("SELECT contato FROM Contato contato WHERE contato.responsavel.id = :id")
	public List<Contato> findByIdResponsable(@Param("id")Integer id);
	
	@Transactional(readOnly = true)
	public List<Contato> findByTipoContato(TipoContato tipoContato);

}
