package net.azurewebsites.fatecsjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Preferencia;
import net.azurewebsites.fatecsjc.repository.PreferenciaRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class PreferenciaService {
	
	@Autowired
	PreferenciaRepository preferenciaRepository;
	
	public Preferencia findById(Integer id) {
		return preferenciaRepository.buscaPorId(id);
	}

	public List<Preferencia> findAll(){
		return preferenciaRepository.findAll();
	}

	public List<Preferencia> findByArrayId(List<Integer> ids) {
		System.out.println("PREFERENCIA SERVICE:"+ids);
		Optional<List<Preferencia>> preferencias = Optional.ofNullable(preferenciaRepository.findByArrayId(ids));
		System.out.println("PREFERENCIAS: " + preferencias);
		return preferencias.orElseThrow(() -> new ObjectNotFoundException(
				"Preferencia não encontrada!"));
	}
	
	
	public List<Preferencia> findByDescricao(String descricao) {
		Optional<List<Preferencia>> preferencias = Optional.ofNullable(preferenciaRepository.findBydescricaoContains(descricao));
		return preferencias.orElseThrow(() -> new ObjectNotFoundException(
				"Preferencia não encontrada!"));
	}

	public List<Preferencia> findByNome(String nome) {
		Optional<List<Preferencia>> preferencias = Optional.ofNullable(preferenciaRepository.findByNome(nome));
		return preferencias.orElseThrow(() -> new ObjectNotFoundException(
				"Preferencia não encontrada!"));
	}
	
	public Preferencia createPreferencia(Preferencia preferencia) {
		preferenciaRepository.save(preferencia);
		
		return findById(preferencia.getId());
	}
	
	public Preferencia updatePreferencia(Preferencia preferenciaNew) {
		Preferencia preferencia = findById(preferenciaNew.getId());
		
		preferencia.setNome(preferenciaNew.getNome());
		preferencia.setDescricao(preferenciaNew.getDescricao());
		
		return preferenciaRepository.save(preferencia);
	}
	
	public void deletePreferencia(Integer id) {
		findById(id);
		try {
			preferenciaRepository.delete(findById(id));
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não foi possivel excluir esta preferencia.");
		}
	}

}
