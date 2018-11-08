package net.azurewebsites.fatecsjc.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Localidade;
import net.azurewebsites.fatecsjc.entity.TipoLocalidade;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Integer>{
	
	@Transactional(readOnly = true)
	@Query("SELECT localidade FROM Localidade localidade WHERE localidade.id = :id")
	public Localidade buscaPorId(@Param("id")Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT localidade FROM Localidade localidade WHERE lower(localidade.nome) LIKE lower(concat(:nomeBusca,'%'))")
	public List<Localidade> findByNomeList(@Param("nomeBusca") String nomeBusca);
	
	@Transactional(readOnly = true)
	@Query("SELECT localidade FROM Localidade localidade WHERE localidade.cep.cidade.nome = :nomeCidade AND lower(localidade.nome) LIKE lower(concat(:nomeBusca,'%'))")
	public LinkedList<Localidade> findByNomeAndCidadeList(@Param("nomeCidade") String nomeCidade, @Param("nomeBusca") String nomeBusca);
	
	@Transactional(readOnly = true)
	public List<Localidade> findBytipoLocalidade(TipoLocalidade tipoLocalidade);
	
	@Transactional(readOnly = true)
	public Localidade findBynome(String nome);

}
