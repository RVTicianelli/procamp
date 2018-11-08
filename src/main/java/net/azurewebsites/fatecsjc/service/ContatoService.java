package net.azurewebsites.fatecsjc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Contato;
import net.azurewebsites.fatecsjc.entity.dto.ContatoLocalidadeDTO;	
import net.azurewebsites.fatecsjc.entity.dto.ContatoResponsavelDTO;

import net.azurewebsites.fatecsjc.repository.ContatoRepository;
import net.azurewebsites.fatecsjc.repository.TipoContatoRepository;
import net.azurewebsites.fatecsjc.repository.ResponsavelRepository;
import net.azurewebsites.fatecsjc.repository.LocalidadeRepository;

import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository contatoRepository;
	
	@Autowired
	TipoContatoRepository tipoContatoRepository;
	
	@Autowired
	ResponsavelRepository responsavelRepository;
	
	@Autowired
	LocalidadeRepository localidadeRepository;
	
	public Contato findById(Integer id) {
		Optional<Contato> contatos = Optional.ofNullable(contatoRepository.buscaPorId(id));
		return contatos.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não encontrado!"));
	}

	public List<Contato> findAll() {
		return contatoRepository.findAll();
	}
	
	public List<Contato> findByContato(String contato) {
		Optional<List<Contato>> contatos = Optional.ofNullable(contatoRepository.findByContato(contato));
		return contatos.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não encontrado!"));
	}
	
	public List<Contato> findByValor(String valor) {
		Optional<List<Contato>> contatos = Optional.ofNullable(contatoRepository.findByValor(valor));
		return contatos.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não encontrado!"));
	}
	
	public List<Contato> findByLocalidade(Integer id) {
		Optional<List<Contato>> contatos = Optional.ofNullable(contatoRepository.findByLocalidade(localidadeRepository.buscaPorId(id)));
		return contatos.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não encontrado."));
	}
	
	public List<Contato> findByResponsavel(Integer id) {
		Optional<List<Contato>> contatos = Optional.ofNullable(contatoRepository.findByResponsavel(responsavelRepository.buscaPorId(id)));
		return contatos.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não encontrado."));
	}
	
	public List<Contato> findByIdResponsable(Integer id) {
		Optional<List<Contato>> contatos = Optional.ofNullable(contatoRepository.findByIdResponsable(id));
		return contatos.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não encontrado."));
	}	
	
	public List<Contato> findByTipoContato(Integer id) {
		Optional<List<Contato>> contatos = Optional.ofNullable(contatoRepository.findByTipoContato(tipoContatoRepository.buscaPorId(id)));
		return contatos.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não encontrado."));
	}
		
	public Contato createContatoResponsavel(ContatoResponsavelDTO conResDto) {
		Contato contato = contatoFromContResponsavel(conResDto);
		contato.setId(null);
		contato = contatoRepository.save(contato);
		return findById(contato.getId());
	}
	
	public Contato createContatoLocalidade(ContatoLocalidadeDTO conLocDto) {
		Contato contato = contatoFromContLocalidade(conLocDto);
		contato.setId(null);
		contato = contatoRepository.save(contato);
		return findById(contato.getId());
	}
	
	public Contato updateContato(Contato cont) {
		Contato contato = findById(cont.getId());
		
		contato.setContato(cont.getContato());
		contato.setValor(cont.getValor());
		contato.setLocalidade(cont.getLocalidade());
		contato.setResponsavel(cont.getResponsavel());
		contato.setTipoContato(cont.getTipoContato());
		
		return contatoRepository.save(contato);
	}
	
	public void deleteContato(Contato cont) {
		Integer id = cont.getId();
		findById(id);
		
		try {
			contatoRepository.deleteById(id);
		} catch (DataIntegrityException a) {
			throw new DataIntegrityException("Não foi possivel excluir.");
		}
	}
	
	public Contato contatoFromContResponsavel(ContatoResponsavelDTO conResDto) {
		Contato contato = new Contato();
		contato.setContato(conResDto.getContato());
		contato.setValor(conResDto.getValor());
		contato.setResponsavel(responsavelRepository.buscaPorId(conResDto.getResponsavel()));
		contato.setTipoContato(tipoContatoRepository.buscaPorId(conResDto.getTipoContato()));
		
		return contato;		
	}	
	
	public Contato contatoFromContLocalidade(ContatoLocalidadeDTO conLocDto) {
		Contato contato = new Contato();
		contato.setContato(conLocDto.getContato());
		contato.setValor(conLocDto.getValor());
		contato.setLocalidade(localidadeRepository.buscaPorId(conLocDto.getLocalidade()));
		contato.setTipoContato(tipoContatoRepository.buscaPorId(conLocDto.getTipoContato()));
		
		return contato;		
	}

}
