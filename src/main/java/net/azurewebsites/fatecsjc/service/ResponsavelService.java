package net.azurewebsites.fatecsjc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Responsavel;
import net.azurewebsites.fatecsjc.entity.dto.ResponsavelFisicoDTO;
import net.azurewebsites.fatecsjc.entity.dto.ResponsavelJuridicoDTO;

import net.azurewebsites.fatecsjc.repository.ResponsavelRepository;
import net.azurewebsites.fatecsjc.repository.TipoResponsavelRepository;
import net.azurewebsites.fatecsjc.repository.CepRepository;

import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class ResponsavelService {
	
	@Autowired
	ResponsavelRepository responsavelRepository;
	
	@Autowired
	CepRepository cepRepository;
	
	@Autowired
	TipoResponsavelRepository tipoResponsavelRepository;
	
	
	public Responsavel findById(Integer id) {
		Optional<Responsavel> responsavel = Optional.ofNullable(responsavelRepository.buscaPorId(id));
		return responsavel.orElseThrow(() -> new ObjectNotFoundException(
				"Responsavel não encontrado! Id: " + id + ". Tipo: " + Responsavel.class.getName()));
	}

	public List<Responsavel> findAll() {
		return responsavelRepository.findAll();
	}
	
	public List<Responsavel> findByNome(String nome) {
		Optional<List<Responsavel>> responsaveis  = Optional.ofNullable(responsavelRepository.findBynome(nome));
		return responsaveis.orElseThrow(() -> new ObjectNotFoundException(
				"Responsavel não encontrado!"));
	}
	
	public LinkedList<Responsavel> findByNomeContainsAndType(Integer id, String nome){
		LinkedList<Responsavel> responsaveis = responsavelRepository.findByNomeContainsAndType(id, nome);
		
		return responsaveis;
	}
	
	public Responsavel findByCpf(String cpf) {
		Optional<Responsavel> responsavel  = Optional.ofNullable(responsavelRepository.findByCpf(cpf));
		return responsavel.orElseThrow(() -> new ObjectNotFoundException(
				"Responsavel não encontrado! CPF: " + cpf));
	}
	
	public Responsavel findByCnpj(String cnpj) {
		Optional<Responsavel> responsavel  = Optional.ofNullable(responsavelRepository.findByCnpj(cnpj));
		return responsavel.orElseThrow(() -> new ObjectNotFoundException(
				"Responsavel não encontrado! CNPJ: " + cnpj));
	}
	
	public List<Responsavel> findByRazaoSocial(String razaoSocial) {
		Optional<List<Responsavel>> responsaveis  = Optional.ofNullable(responsavelRepository.findByRazaoSocial(razaoSocial));
		return responsaveis.orElseThrow(() -> new ObjectNotFoundException(
				"Responsavel não encontrado! Razao Social: " + razaoSocial));
	}
	
	public List<Responsavel> findByTipoResponsavel(Integer id) {
		Optional<List<Responsavel>> responsaveis = Optional.ofNullable(responsavelRepository.findByTipoResponsavel(tipoResponsavelRepository.buscaPorId(id)));
		return responsaveis.orElseThrow(() -> new ObjectNotFoundException(
				"Não houverem resultados para a restrição de pesquisa."));
	}
	
	public Responsavel createResponsavelFisico(ResponsavelFisicoDTO respFisDto) {
		Responsavel responsavel = ResponsavelFromRespFisDto(respFisDto);
		if(responsavelRepository.findByCpf(responsavel.getCpf()) == null) {
			responsavel.setId(null);
			responsavel = responsavelRepository.save(responsavel);
			return findById(responsavel.getId());
		}else {
			throw new DataIntegrityException("CPF já cadastrado.");
		}
	}
	
	public Responsavel createResponsavelJuridico(ResponsavelJuridicoDTO respJurDto) {
		Responsavel responsavel = ResponsavelFromRespJurDto(respJurDto);
		if(responsavelRepository.findByCnpj(responsavel.getCnpj()) == null) {
			responsavel.setId(null);
			responsavel = responsavelRepository.save(responsavel);
			return findById(responsavel.getId());
		}else {
			throw new DataIntegrityException("CNPJ já cadastrado.");
		}
	}
	
	public Responsavel updateResponsavel(Responsavel resp) {
		Responsavel responsavel = findById(resp.getId());
		
		responsavel.setNome(resp.getNome());
		responsavel.setCpf(resp.getCpf());
		responsavel.setCnpj(resp.getCnpj());
		responsavel.setDataNascimento(resp.getDataNascimento());
		responsavel.setRazaoSocial(resp.getRazaoSocial());
		responsavel.setSexo(resp.getSexo());
		responsavel.setNumero(resp.getNumero());
		responsavel.setComplemento(resp.getComplemento());
		responsavel.setCep(resp.getCep());
		responsavel.setTipoResponsavel(resp.getTipoResponsavel());
		
		return responsavelRepository.save(responsavel);
	}
	
	public void deleteResponsavel(Responsavel resp) {
		Integer id = resp.getId();
		findById(id);
		
		try {
			responsavelRepository.deleteById(id);
		} catch (DataIntegrityException a) {
			throw new DataIntegrityException("Não foi possivel excluir.");
		}
	}
	
	public Responsavel ResponsavelFromRespFisDto(ResponsavelFisicoDTO resDto) {
		Responsavel responsavel = new Responsavel();
		responsavel.setNome(resDto.getNome());
		responsavel.setCpf(resDto.getCpf());
		responsavel.setDataNascimento(resDto.getDataNascimento());
		responsavel.setSexo(resDto.getSexo());
		responsavel.setNumero(resDto.getNumero());
		responsavel.setComplemento(resDto.getComplemento());
		responsavel.setTipoResponsavel(tipoResponsavelRepository.buscaPorId(resDto.getTipoResponsavel()));
		responsavel.setCep(cepRepository.buscaPorId(resDto.getCep()));
		
		return responsavel;
	}
	
	public Responsavel ResponsavelFromRespJurDto(ResponsavelJuridicoDTO resDto) {
		Responsavel responsavel = new Responsavel();
		responsavel.setNome(resDto.getNome());
		responsavel.setCnpj(resDto.getCnpj());
		responsavel.setRazaoSocial(resDto.getRazaoSocial());
		responsavel.setNumero(resDto.getNumero());
		responsavel.setComplemento(resDto.getComplemento());
		responsavel.setTipoResponsavel(tipoResponsavelRepository.buscaPorId(resDto.getTipoResponsavel()));
		responsavel.setCep(cepRepository.buscaPorId(resDto.getCep()));
		
		return responsavel;
	}
	

}
