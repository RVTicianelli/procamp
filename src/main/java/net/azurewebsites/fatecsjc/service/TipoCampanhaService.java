package net.azurewebsites.fatecsjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.TipoCampanha;
import net.azurewebsites.fatecsjc.repository.TipoCampanhaRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class TipoCampanhaService {
	
	@Autowired
	TipoCampanhaRepository tipoCampanhaRepository;
	
	public TipoCampanha findById(Integer id) {
		return tipoCampanhaRepository.buscaPorId(id);
	}

	public List<TipoCampanha> findAll(){
		return tipoCampanhaRepository.findAll();
	}
	
	public List<TipoCampanha> findBydescricao(String descricao) {
		Optional<List<TipoCampanha>> tipoCampanha = Optional.ofNullable(tipoCampanhaRepository.findBydescricaoContains(descricao));
		return tipoCampanha.orElseThrow(() -> new ObjectNotFoundException(
				"Tipo de localidade não encontrada! Descricao: " + descricao + ". Tipo: " + TipoCampanha.class.getName()));
	}
	
	public TipoCampanha create(String descricao) {
		TipoCampanha tipoCampanha = new TipoCampanha(null, descricao);
		
		if(tipoCampanhaRepository.findBydescricao(descricao).isEmpty()) {
			tipoCampanha = tipoCampanhaRepository.save(tipoCampanha); 
		}else {
			throw new DataIntegrityException("NÃO É POSSIVEL CADASTRAR ESSE FUNCIONARIO");
		}
		return findById(tipoCampanha.getId());
	}
	
	public TipoCampanha updateTipoCampanha(TipoCampanha tipoCampanhaNew) {
		TipoCampanha tipoCampanha = findById(tipoCampanhaNew.getId());
		tipoCampanha.setDescricao(tipoCampanhaNew.getDescricao());
		
		return tipoCampanhaRepository.save(tipoCampanha);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			tipoCampanhaRepository.delete(findById(id));
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSE FUNCIONARIO.");
		}
	}

}
