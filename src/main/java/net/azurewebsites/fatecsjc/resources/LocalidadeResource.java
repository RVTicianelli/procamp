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

import net.azurewebsites.fatecsjc.entity.Localidade;
import net.azurewebsites.fatecsjc.entity.dto.LocalidadeDTO;
import net.azurewebsites.fatecsjc.entity.dto.mapa.PontoCampanhaEndereco;
import net.azurewebsites.fatecsjc.entity.dto.mapa.PontoCampanhaGeo;
import net.azurewebsites.fatecsjc.service.LocalidadeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/localidade")
public class LocalidadeResource {
	@Autowired
	private LocalidadeService localidadeService;
	
	@RequestMapping(value = "/buscalocalidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<Localidade> findById(@PathVariable Integer id) {
		Localidade localidade = localidadeService.findById(id);
		return ResponseEntity.ok(localidade);
	}
	
	@RequestMapping(value = "/buscalocalidade", method = RequestMethod.GET)
	public ResponseEntity<List<Localidade>> findAll() {
		List<Localidade> localidades = localidadeService.findAll();
		return ResponseEntity.ok(localidades);
	}

	@RequestMapping(value = "/buscalocalidade/tipolocalidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Localidade>> findByTipoLocalidade(@PathVariable Integer id) {
		List<Localidade> localidade = localidadeService.findByTipoLocalidade(id);
		return ResponseEntity.ok(localidade);
	}

	@RequestMapping(value = "/buscalocalidade/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Localidade> findByNome(@PathVariable String nome) {
		Localidade localidade = localidadeService.findByNome(nome);
		return ResponseEntity.ok(localidade);
	}
	
	@RequestMapping(value = "/buscalocalidade/cidade/{cidade}/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Localidade>> findByNomeAndCidade(@PathVariable String cidade, @PathVariable String nome) {
		List<Localidade> localidade = localidadeService.findByNomeAndCidadeList(cidade, nome);
		return ResponseEntity.ok(localidade);
	}
	
	@RequestMapping(value = "/buscalocalidade/nomelist/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Localidade>> findByNomeList(@PathVariable String nome) {
		List<Localidade> localidade = localidadeService.findByNomeList(nome);
		return ResponseEntity.ok(localidade);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Integer createLocalidade(@RequestBody LocalidadeDTO locDto) {
		Localidade localidade = localidadeService.createLocalidade(locDto);
		Integer newId = localidade.getId();
		return newId;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletaLocalidade(@PathVariable Integer id) {
		localidadeService.deleteLocalidade(localidadeService.findById(id));
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Localidade> updateLocalidade(@PathVariable Integer id,@RequestBody LocalidadeDTO locDto){
		Localidade localidade = localidadeService.updateLocalidade(id ,locDto);
		return ResponseEntity.ok(localidade);
	}
	
 	@RequestMapping(value = "/gerapontos", method = RequestMethod.POST)
	public ResponseEntity<List<PontoCampanhaGeo>> geraPontos(@RequestBody PontoCampanhaEndereco listaEndereco) {
 		List<PontoCampanhaGeo> listEnderecosGeo = localidadeService.ConvertePontosCampanha(listaEndereco);
 		return ResponseEntity.ok().body(listEnderecosGeo);
 	}

}
