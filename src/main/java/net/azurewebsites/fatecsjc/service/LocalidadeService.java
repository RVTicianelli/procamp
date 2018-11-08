package net.azurewebsites.fatecsjc.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Localidade;
import net.azurewebsites.fatecsjc.entity.dto.LocalidadeDTO;
import net.azurewebsites.fatecsjc.entity.dto.mapa.PontoCampanhaEndereco;
import net.azurewebsites.fatecsjc.entity.dto.mapa.PontoCampanhaGeo;
import net.azurewebsites.fatecsjc.repository.LocalidadeRepository;
import net.azurewebsites.fatecsjc.repository.TipoLocalidadeRepository;
import net.azurewebsites.fatecsjc.repository.CepRepository;

import net.azurewebsites.fatecsjc.service.CepService;

import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class LocalidadeService {
	
	@Autowired
	LocalidadeRepository localidadeRepository;
	
	@Autowired
	TipoLocalidadeRepository tipoLocalidadeRepository;
	
	@Autowired
	CepRepository cepRepository;
	
	@Autowired
	CepService cepService;
	
	public Localidade findById(Integer id) {
		Optional<Localidade> localidade = Optional.ofNullable(localidadeRepository.buscaPorId(id));
		return localidade.orElseThrow(() -> new ObjectNotFoundException(
				"Localidade não encontrada! Id: " + id + ". Tipo: " + Localidade.class.getName()));
	}

	public List<Localidade> findAll() {
		return localidadeRepository.findAll();
	}
	
	public Localidade findByNome(String nome) {
		Localidade localidades  = localidadeRepository.findBynome(nome);
		return localidades;
	}
	
	public List<Localidade> findByNomeList(String nome) {
		List<Localidade> localidades  = localidadeRepository.findByNomeList(nome);
		return localidades;
	}
	
	public LinkedList<Localidade> findByNomeAndCidadeList(String cidade, String nome){
		LinkedList<Localidade> localidades = localidadeRepository.findByNomeAndCidadeList(cidade, nome);
		
		return localidades;
	}
	
	public List<Localidade> findByTipoLocalidade(Integer id) {
		Optional<List<Localidade>> Localidades = Optional.ofNullable(localidadeRepository.findBytipoLocalidade(tipoLocalidadeRepository.buscaPorId(id)));
		return Localidades.orElseThrow(() -> new ObjectNotFoundException(
				"Não houverem resultados para a restrição de pesquisa" + "Metodo: Busca por tipo empresa. Tipo Empresa" + id));
	}
	
	public Localidade createLocalidade(LocalidadeDTO locDto) {
		Localidade localidade = localidadeFromDTO(locDto);
		localidade.setId(null);
		localidadeRepository.save(localidade);
		
		return findById(localidade.getId());
	}
	
	public Localidade updateLocalidade(Integer id, LocalidadeDTO locDto) {
		Localidade localidade = findById(id);
		
		if(!(localidade == null)) {
			localidade.setNome(locDto.getNome());
			localidade.setPontoReferencia(locDto.getPontoReferencia());
			localidade.setNumero(locDto.getNumero());
			localidade.setComplemento(locDto.getPontoReferencia());
			localidade.setCep(cepService.findById(locDto.getCep()));
			localidade.setTipoLocalidade(tipoLocalidadeRepository.buscaPorId(locDto.getTipoLocalidade()));
			
			return localidadeRepository.save(localidade);
		} else {
			throw new ObjectNotFoundException("Localidade não encontrada");
		}
	}
	
	public void deleteLocalidade(Localidade loc) {
		findById(loc.getId());
		
		try {
			localidadeRepository.delete(loc);
		} catch (DataIntegrityException a) {
			throw new DataIntegrityException("Não foi possivel excluir esta localidade");
		}
	}
	
	public Localidade localidadeFromDTO(LocalidadeDTO locDto) {
		Localidade localidade = new Localidade();
		localidade.setId(null);
		localidade.setNome(locDto.getNome());
		localidade.setPontoReferencia(locDto.getPontoReferencia());
		localidade.setNumero(locDto.getNumero());
		localidade.setComplemento(locDto.getComplemento());
		localidade.setCep(cepService.findById(locDto.getCep()));
		localidade.setTipoLocalidade(tipoLocalidadeRepository.buscaPorId(locDto.getTipoLocalidade()));
		
		return localidade;
	}
	
	
	//MAPAS
	
	public List<PontoCampanhaGeo> ConvertePontosCampanha(PontoCampanhaEndereco pontoEndereco) {
		List<PontoCampanhaGeo> pontos = new ArrayList<PontoCampanhaGeo>();
		
		for(int i = 0; i < pontoEndereco.getListaEnderecos().size(); i ++) {
			String endereco = pontoEndereco.getListaEnderecos().get(i);
			PontoCampanhaGeo ptgeo = BuscaPontoGeo(endereco);
			pontos.add(ptgeo);
		}		
		
		return pontos;	
	}
	
	public PontoCampanhaGeo BuscaPontoGeo(String endereco) {		
		JsonObject response  = null;
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String url = "https://maps.googleapis.com/maps/api/geocode/json?"
						+"address="+endereco+""
						+"&key=AIzaSyBwpP0OxFfO5KdqEjGcbEqapNxpOLn_KjM";
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			HttpEntity httpEntity = httpResponse.getEntity();
			
			response = Json.createReader(httpEntity.getContent()).readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return ConvertePontoGeo(response);
	}
	
	public PontoCampanhaGeo ConvertePontoGeo(JsonObject response) {
		PontoCampanhaGeo ptGeo = new PontoCampanhaGeo();
		String latitude;
		String longitude;
		String endereco;
		
		if(response.getString("status").equals("OK")) {
			JsonArray resultsArray = (JsonArray) response.get("results");
			JsonObject resultsObj = (JsonObject) resultsArray.get(0);
			JsonObject geometryObj = (JsonObject) resultsObj.get("geometry");		
			JsonObject locationObject = (JsonObject) geometryObj.get("location");
			
			endereco = resultsObj.get("formatted_address").toString();
			latitude = locationObject.get("lat").toString();
			longitude = locationObject.get("lng").toString();
			
			ptGeo.setEndereco(endereco);
			ptGeo.setLatitude(latitude);
			ptGeo.setLongitude(longitude);
			
			return ptGeo;
		}
		
		return null;
	}

}
