package net.azurewebsites.fatecsjc.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Cidade;
import net.azurewebsites.fatecsjc.repository.CidadeRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;

@Service
public class CidadeService {
	@Autowired(required = true)
	CidadeRepository cidadeRepository;
	
	@Autowired(required = true)
	EstadoService estadoService;
	
	
	public Cidade findById(Integer id) {
		Cidade cidade = cidadeRepository.buscaPorId(id);
		System.out.println("teste");
		
		return cidade;
	}
	
	
	public List<Cidade> findAll(){
		return cidadeRepository.findAll();
	}
	
	
	public LinkedList<Cidade> findByNomeContainsAndUf(Integer id, String nome){
		LinkedList<Cidade> cidades = cidadeRepository.findByNomeContainsAndUf(id, nome);
		
		return cidades;
	}
	
	
	public List<Cidade> findByEstado(String uf){
		LinkedList<Cidade> cidades = cidadeRepository.findByEstado(estadoService.findByUf(uf));
		
		return cidades;
	}
	
	
	public LinkedList<Cidade> findByNomeCidadeEstadoSigla(String nomeCidade, String ufCidade){
		LinkedList<Cidade> cidades = cidadeRepository.findCidadesEstadoIdNomeCidades
																			(estadoService.findByUf(ufCidade).getId(), 
																					nomeCidade);
		return cidades;
	}
	
	
	public Cidade createCidade(Cidade cidade){
		cidade.setId(null);
		cidade = cidadeRepository.save(cidade);
		return findById(cidade.getId());
	}
	
	
	public Cidade updateCidade(Cidade updateCidade) {
		Cidade cidade = findById(updateCidade.getId());
		
		cidade.setNome(updateCidade.getNome());
		cidade.setEstado(updateCidade.getEstado());
		
		return cidadeRepository.save(cidade);
		
	}
	
	
	public void DeletarCidade(Integer id) {
		findById(id);
		
		try {
			cidadeRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR UMA CIDADE QUE POSSUA CEPs VINCULADOS");
		}
	}
	
	
	public Cidade findByNomeCidadeSiglaEstadoAux( String ufCidade , String nomeCidade) {
		try {
			return cidadeRepository.findCidadesEstadoIdNomeCidadesAux(estadoService.findByUf(ufCidade).getId(), nomeCidade);
		} catch (NullPointerException e) {
			return null;
		}
	}
}
