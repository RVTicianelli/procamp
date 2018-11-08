package net.azurewebsites.fatecsjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.TipoResponsavel;
import net.azurewebsites.fatecsjc.repository.TipoResponsavelRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class TipoResponsavelService {
	@Autowired
	TipoResponsavelRepository tipoResponsavelRepository;
	
	public TipoResponsavel findById(Integer id) {
		return tipoResponsavelRepository.buscaPorId(id);
	}

	public List<TipoResponsavel> findAll(){
		return tipoResponsavelRepository.findAll();
	}
	
	public List<TipoResponsavel> findBydescricao(String descricao) {
		Optional<List<TipoResponsavel>> tipoResponsavel = Optional.ofNullable(tipoResponsavelRepository.findBydescricaoContains(descricao));
		return tipoResponsavel.orElseThrow(() -> new ObjectNotFoundException(
				"Tipo de responsavel não encontrado! Descricao: " + descricao + ". Tipo: " + TipoResponsavel.class.getName()));
	}
	
	public TipoResponsavel create(String descricao) {
		TipoResponsavel tipoResponsavel = new TipoResponsavel(null, descricao);
		
		if(tipoResponsavelRepository.findBydescricao(descricao).isEmpty()) {
			tipoResponsavel = tipoResponsavelRepository.save(tipoResponsavel); 
		}else {
			throw new DataIntegrityException("Não foi possivel cadastrar esse tipo de responsavel");
		}
		return findById(tipoResponsavel.getId());
	}

	public TipoResponsavel updateTipoResponsavel(TipoResponsavel tipoResponsavelNew) {
		TipoResponsavel tipoResponsavel = findById(tipoResponsavelNew.getId());
		tipoResponsavel.setDescricao(tipoResponsavelNew.getDescricao());
		
		return tipoResponsavelRepository.save(tipoResponsavel);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			tipoResponsavelRepository.delete(findById(id));
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSE FUNCIONARIO.");
		}
	}

}
