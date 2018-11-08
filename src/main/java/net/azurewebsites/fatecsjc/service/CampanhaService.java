package net.azurewebsites.fatecsjc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Localidade;
import net.azurewebsites.fatecsjc.entity.Preferencia;
import net.azurewebsites.fatecsjc.entity.dto.Campanha;
import net.azurewebsites.fatecsjc.entity.dto.CampanhaDTO;
import net.azurewebsites.fatecsjc.repository.CampanhaRepository;
import net.azurewebsites.fatecsjc.repository.ResponsavelRepository;
import net.azurewebsites.fatecsjc.repository.TipoCampanhaRepository;

import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class CampanhaService {
	@Autowired
	CampanhaRepository campanhaRepository;
	
	@Autowired
	ResponsavelRepository responsavelRepository;
	
	@Autowired
	TipoCampanhaRepository tipoCampanhaRespository;
	
	@Autowired
	LocalidadeService localidadeService;
	
	@Autowired
	PreferenciaService preferenciaService;
	
	public Campanha findById(Integer id) {
		Optional<Campanha> localidade = Optional.ofNullable(campanhaRepository.buscaPorId(id));
		return localidade.orElseThrow(() -> new ObjectNotFoundException(
				"Capanha não encontrada! Id: " + id));
	}
	
	public List<Campanha> findAll() {
		return campanhaRepository.findAll();
	}
	
	public List<Campanha> findByNome(String nome) {
		List<Campanha> campanhas = campanhaRepository.findByNome(nome);
		return campanhas;
	}
	
	public List<Campanha> findByDataInicial(String data) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.buscaPorDataInicial(data));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhuma capanha não encontrada!"));
	}
	
	public List<Campanha> findByNomeContains(String nome) {
		List<Campanha> campanhas = campanhaRepository.findByNomeContains(nome);
		return campanhas;
	}
	
	public List<Campanha> findByDataFianl(String data) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.buscaPorDataFinal(data));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhuma capanha não encontrada!"));
	}
	
	public List<Campanha> findByPeriod(String dataInicio, String dataFim) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.buscaPorPeriodo(dataInicio, dataFim));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhuma capanha não encontrada!"));
	}
	
	public List<Campanha> findByCurrent(String data) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.buscaVigentes(data));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhuma capanha não encontrada!"));
	}
	
	public List<Campanha> findByNews(String data) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.buscaNovas(data));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhuma capanha não encontrada!"));		
	}
	
	public List<Campanha> findByNewsAndType(Integer tipoCampanhaId, String data) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.buscaNovasPorTipo(tipoCampanhaId, data));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhuma capanha não encontrada!"));		
	}
	
	public List<Campanha> findByTipoCampanha(Integer id) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.findByTipoCampanha(tipoCampanhaRespository.buscaPorId(id)));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException("Não houveram resultados para a consulta"));
	}
	
	public List<Campanha> findByResponsavel(Integer id) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.findByResponsavel(responsavelRepository.buscaPorId(id)));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException("Não houveram resultados para a consulta")); 
	}
	
	public List<Campanha> findByPreferenciasId(Integer id) {
		Optional<List<Campanha>> campanhas = Optional.ofNullable(campanhaRepository.findByPreferencias_id(id));
		return campanhas.orElseThrow(() -> new ObjectNotFoundException("Nada"));
	}
	
	public Campanha createCampanha(CampanhaDTO campDto) {
		Campanha campanha = campanhaFromDTO(campDto);
		campanha.setId(null);
		campanhaRepository.save(campanha);
		
		return findById(campanha.getId());
	}
	
	public Campanha updadeCampanha(Integer id, CampanhaDTO campDto) {
		Campanha campanhaValida = findById(id);
		
		if(!(campanhaValida == null)) {
			Campanha campanha = campanhaFromDTO(campDto);
			campanha.setId(id);
			
			return campanhaRepository.save(campanha);
		} else {
			throw new ObjectNotFoundException("Campanha não encontrada");
		}
	}
	
	public void deleteCampanha(Integer id) {
		Campanha campanha = findById(id);
		
		try {
			campanhaRepository.delete(campanha);
		} catch (DataIntegrityException a) {
			throw new DataIntegrityException("Não foi possivel excluir esta campanha");
		}
	}
	
	
	public Campanha campanhaFromDTO(CampanhaDTO campDto) { 
		Campanha campanha = new Campanha();
		campanha.setId(null);
		campanha.setNome(campDto.getNome());
		campanha.setDataInicio(campDto.getDataInicio());
		campanha.setDataFim(campDto.getDataFim());
		campanha.setDataCadastro(campDto.getDataCadastro());
		campanha.setDescricao(campDto.getDescricao());
		campanha.setResponsavel(responsavelRepository.buscaPorId(campDto.getResponsavel()));
		campanha.setTipoCampanha(tipoCampanhaRespository.buscaPorId(campDto.getTipoCampanha()));
		
		Set<Localidade> localidades = new HashSet<>();
		for (Integer id : campDto.getLocalidades()) {
			localidades.add(localidadeService.findById(id));
		}
		
		Set<Preferencia> preferencias = new HashSet<>();
		for (Integer id : campDto.getPreferencias()) {
			preferencias.add(preferenciaService.findById(id));
		}
		
		campanha.setLocalidades(localidades);
		campanha.setPreferencias(preferencias);
		
		return campanha;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
