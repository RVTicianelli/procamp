package net.azurewebsites.fatecsjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.TipoContato;
import net.azurewebsites.fatecsjc.repository.TipoContatoRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class TipoContatoService {
	@Autowired
	TipoContatoRepository tipoContatoRepository;
	
	public TipoContato findById(Integer id) {
		return tipoContatoRepository.buscaPorId(id);
	}

	public List<TipoContato> findAll(){
		return tipoContatoRepository.findAll();
	}
	
	public List<TipoContato> findBydescricao(String descricao) {
		Optional<List<TipoContato>> tipoContato = Optional.ofNullable(tipoContatoRepository.findBydescricaoContains(descricao));
		return tipoContato.orElseThrow(() -> new ObjectNotFoundException(
				"Tipo de localidade não encontrada! Descricao: " + descricao + ". Tipo: " + TipoContato.class.getName()));
	}
	
	public TipoContato create(String descricao) {
		TipoContato tipoContato = new TipoContato(null, descricao);
		
		if(tipoContatoRepository.findBydescricao(descricao).isEmpty()) {
			tipoContato = tipoContatoRepository.save(tipoContato); 
		}else {
			throw new DataIntegrityException("NÃO É POSSIVEL CADASTRAR ESSE FUNCIONARIO");
		}
		return findById(tipoContato.getId());
	}
	
	public TipoContato updateTipoContato(TipoContato tipoContatoNew) {
		TipoContato tipoContato = findById(tipoContatoNew.getId());
		tipoContato.setDescricao(tipoContatoNew.getDescricao());
		
		return tipoContatoRepository.save(tipoContato);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			tipoContatoRepository.delete(findById(id));
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSE FUNCIONARIO.");
		}
	}
}
