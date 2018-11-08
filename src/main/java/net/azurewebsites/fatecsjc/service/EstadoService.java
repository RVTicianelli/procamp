package net.azurewebsites.fatecsjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Estado;
import net.azurewebsites.fatecsjc.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired(required = true)
	private EstadoRepository estadoRepository;

	
	public Estado findById(Integer id) {
		Estado estado = estadoRepository.buscaPorId(id);

		return estado;
	}
	
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}
	
	public Estado findByUf(String uf) {		
		Estado estado = estadoRepository.findByuf(uf);
		
		return estado;
	}
	
	public Estado findByNome(String nome) {
		Estado estado = estadoRepository.findBynomeContains(nome);
		
		return estado;
	}
	
	
	
	public Estado createEstado(Estado estado) {
		estado.setId(null);
		estado = estadoRepository.save(estado);
		return findById(estado.getId());
	}
	
	
	public Estado updateEstado(Estado updateEstado) {
		Estado estado = findById(updateEstado.getId());
		
		estado.setNome(updateEstado.getNome());
		estado.setUf(updateEstado.getUf());
		
		return estadoRepository.save(estado);
	}
	
	public void deleteEstado(Integer id) {
		findById(id);		
		try {
			estadoRepository.deleteById(id);
		}catch(DataIntegrityViolationException erro){
			throw new net.azurewebsites.fatecsjc.service.exception.DataIntegrityException(
					"NAO E POSSIVEL EXCLUIR UM ESTADO QUE POSSUA CIDADES VINCULADAS");
		}
	}
	
	public Estado findByUfAux(String uf) {
		try {
			return estadoRepository.findByUfAux(uf);
		} catch (NullPointerException erro) {
			return null;
		}
	}
}
