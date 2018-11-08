package net.azurewebsites.fatecsjc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.azurewebsites.fatecsjc.entity.dto.Campanha;
import net.azurewebsites.fatecsjc.entity.dto.CampanhaDTO;
import net.azurewebsites.fatecsjc.service.CampanhaService;

@CrossOrigin
@RestController
@RequestMapping(value = "/campanha")
public class CampanhaResource {
	@Autowired
	CampanhaService campanhaService;
	
	
	@RequestMapping(value = "/buscacampanha/{id}", method = RequestMethod.GET)
	public ResponseEntity<Campanha> findById(@PathVariable Integer id) {
		Campanha campanha = campanhaService.findById(id);
		return ResponseEntity.ok(campanha);
	}
	
	@RequestMapping(value = "/buscacampanha", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findAll() {
		List<Campanha> campanhas = campanhaService.findAll();
		return ResponseEntity.ok(campanhas);
	}
	
	@RequestMapping(value = "/buscacampanha/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByNome(@PathVariable String nome) {
		List<Campanha> campanhas = campanhaService.findByNome(nome);
		return ResponseEntity.ok(campanhas);
	}
	
	@RequestMapping(value = "/buscacampanha/nomelist/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByNomeContains(@PathVariable String nome) {
		List<Campanha> campanhas = campanhaService.findByNomeContains(nome);
		return ResponseEntity.ok(campanhas);
	}
	
	@RequestMapping(value = "/buscacampanha/datainicio/{data}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByDataInicio(@PathVariable String data) {
		List<Campanha> campanhas = campanhaService.findByDataInicial(data);
		return ResponseEntity.ok(campanhas);
	}
	
	@RequestMapping(value = "/buscacampanha/datafinal/{data}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByDataFinal(@PathVariable String data) {
		List<Campanha> campanhas = campanhaService.findByDataFianl(data);
		return ResponseEntity.ok(campanhas);
	}

	@RequestMapping(value = "/buscacampanha/periodo/{dataInicio}/{dataFim}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByPeriod(@PathVariable String dataInicio, @PathVariable String dataFim) {
		List<Campanha> campanhas = campanhaService.findByPeriod(dataInicio, dataFim);
		return ResponseEntity.ok(campanhas);
	}
	
	@RequestMapping(value = "/buscacampanha/vigentes/{data}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByCurrent(@PathVariable String data) {
		List<Campanha> campanhas = campanhaService.findByCurrent(data);
		return ResponseEntity.ok(campanhas);
	}
	
	@RequestMapping(value = "/buscacampanha/novas/tipocampanha/{tipoCampanhaId}/{data}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByDataAndType(@PathVariable Integer tipoCampanhaId, @PathVariable String data) {
		List<Campanha> campanhas = campanhaService.findByNewsAndType(tipoCampanhaId, data);
		return ResponseEntity.ok(campanhas);
	}
	
	@RequestMapping(value = "/buscacampanha/preferencias/{preferencias}", method = RequestMethod.GET)
	public ResponseEntity<List<Campanha>> findByPreferences(@PathVariable Integer preferencias) {
		List<Campanha> campanhas = campanhaService.findByPreferenciasId(preferencias);
		return ResponseEntity.ok(campanhas);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/insere", method = RequestMethod.POST)
	public Integer createCampanha(@RequestBody CampanhaDTO campDto) {
		Campanha campanha = campanhaService.createCampanha(campDto);
		Integer id = campanha.getId();
		return id;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/deleta/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletaCampanha(@PathVariable Integer id) {
		campanhaService.deleteCampanha(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Campanha> updateCampanha(@PathVariable Integer id,@RequestBody CampanhaDTO campDto){
		Campanha campanha = campanhaService.updadeCampanha(id ,campDto);
		return ResponseEntity.ok(campanha);
	}	

}
