package net.azurewebsites.fatecsjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.TipoLocalidade;
import net.azurewebsites.fatecsjc.repository.TipoLocalidadeRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class TipoLocalidadeService {

	@Autowired
	TipoLocalidadeRepository tipoLocalidadeRepository;
	
	public TipoLocalidade findById(Integer id) {
		return tipoLocalidadeRepository.buscaPorId(id);
	}

	public List<TipoLocalidade> findAll(){
		return tipoLocalidadeRepository.findAll();
	}
	
	public List<TipoLocalidade> findBydescricao(String descricao) {
		Optional<List<TipoLocalidade>> tipoLocalidades = Optional.ofNullable(tipoLocalidadeRepository.findBydescricaoContains(descricao));
		return tipoLocalidades.orElseThrow(() -> new ObjectNotFoundException(
				"Tipo de localidade não encontrada! Descricao: " + descricao + ". Tipo: " + TipoLocalidade.class.getName()));
	}
	
	public TipoLocalidade create(String descricao) {
		TipoLocalidade tipoLocalidade = new TipoLocalidade(null, descricao);
		
		if(tipoLocalidadeRepository.findBydescricao(descricao).isEmpty()) {
			tipoLocalidade = tipoLocalidadeRepository.save(tipoLocalidade); 
		}else {
			throw new DataIntegrityException("NÃO É POSSIVEL CADASTRAR ESSE FUNCIONARIO");
		}
		return findById(tipoLocalidade.getId());
	}
	
	public TipoLocalidade updateTipoLocalidade(TipoLocalidade tipoLocalidadeNew) {
		TipoLocalidade tipoLocalidade = findById(tipoLocalidadeNew.getId());
		tipoLocalidade.setDescricao(tipoLocalidadeNew.getDescricao());
		
		return tipoLocalidadeRepository.save(tipoLocalidade);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			tipoLocalidadeRepository.delete(findById(id));
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSE FUNCIONARIO.");
		}
	}
	

}
