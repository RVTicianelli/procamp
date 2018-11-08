package net.azurewebsites.fatecsjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Cep;
import net.azurewebsites.fatecsjc.entity.Cidade;
import net.azurewebsites.fatecsjc.repository.CepRepository;
import net.azurewebsites.fatecsjc.repository.CidadeRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;

@Service
public class CepService {

	@Autowired
	CepRepository cepRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;

	public Cep findById(Integer id) {
		Cep cep = cepRepository.buscaPorId(id);

		return cep;
	}

	public List<Cep> findAll() {
		return cepRepository.findAll();
	}

	public Cep findByCep(String cepBusca) {
		cepBusca = formataCep(cepBusca);
		Cep cep = cepRepository.findBycep(cepBusca);
		
		return cep;
	}
	
	public List<Cep> findByCepList(String cepBusca) {
		cepBusca = formataCep(cepBusca);
		List<Cep> cep = cepRepository.findByCepList(cepBusca);
		
		return cep;
	}

	public List<Cep> findByNomeRua(String nomeRua) {
		return cepRepository.findBynomeRua(nomeRua);
	}
	
	public List<Cep> findByCidade(String cidade){
		Cidade cidadeBusca = cidadeRepository.findBynome(cidade);
		return cepRepository.findByCidade(cidadeBusca);
	}

	public Cep createCep(Cep cep) {
		cep.setId(null);
		cep = cepRepository.save(cep);
		return findById(cep.getId());
	}

	public Cep updateCep(Cep updateCep) {
		Cep cep = findById(updateCep.getId());

		cep.setCep(updateCep.getCep());
		cep.setCidade(updateCep.getCidade());
		cep.setNomeRua(updateCep.getNomeRua());
		cep.setBairro(updateCep.getBairro());

		return cepRepository.save(cep);
	}

	public void deletarCep(Integer id) {
		findById(id);

		try {
			cepRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR UM CEP QUE POSSUA ENTREGAS VINCULADAS.");
		}
	}
	
	public String cepToStringEndereco(String cepBusca, String numLogradouro) {
		Cep cep = new Cep();
		StringBuilder builder = new StringBuilder();
		
		
		cep = findByCep(cepBusca);
		
		builder.append(cep.getNomeRua());
		builder.append(" ");
		builder.append(numLogradouro);
		builder.append(", ");
		builder.append(cep.getBairro());
		builder.append(", ");
		builder.append(cep.getCidade().getNome());
		builder.append(", ");
		builder.append(cep.getCidade().getEstado().getNome());
		builder.append(", ");
		builder.append(cep.getCep());
		
		return builder.toString();
		
	}
	
	public String formataCep(String cep) {
		return cep.replace("-", "");
	}

}
