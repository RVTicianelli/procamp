package net.azurewebsites.fatecsjc.resources;

import java.net.URI;
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

import net.azurewebsites.fatecsjc.entity.TipoLocalidade;
import net.azurewebsites.fatecsjc.service.TipoLocalidadeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/tipolocalidade")
public class TipoLocalidadeResource {
	@Autowired
	TipoLocalidadeService tipoLocalidadeService;
	
	@RequestMapping(value="/buscatipolocalidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoLocalidade> findById(@PathVariable Integer id){
		TipoLocalidade tipoLocalidade = tipoLocalidadeService.findById(id);
		return ResponseEntity.ok(tipoLocalidade);
	}
	
	@RequestMapping(value = "/buscatipolocalidade", method = RequestMethod.GET)
	public ResponseEntity<List<TipoLocalidade>> findAll(){
		List<TipoLocalidade> tipoLocalidades = tipoLocalidadeService.findAll();
		return ResponseEntity.ok().body(tipoLocalidades);
	}
	
	@RequestMapping(value="/buscatipolocalidade/descricao/{descricao}",method = RequestMethod.GET)
	public ResponseEntity<List<TipoLocalidade>> findByDescricaoContains(@PathVariable String descricao){
		List<TipoLocalidade> tipoLocalidades = tipoLocalidadeService.findBydescricao(descricao);
		return ResponseEntity.ok(tipoLocalidades);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/insert/{descricao}",method = RequestMethod.POST)
	public ResponseEntity<Void> createTipoLocalidade(@PathVariable String descricao) {
		TipoLocalidade tipoLocalidade = tipoLocalidadeService.create(descricao);
		URI uri = URI.create("/tipolocalidade" + "/buscatipolocalidade/" + tipoLocalidade.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<TipoLocalidade> updateTipoLocalidade(@RequestBody TipoLocalidade tipoLocalidadeNew) {
		TipoLocalidade tipoLocalidade = tipoLocalidadeService.updateTipoLocalidade(tipoLocalidadeNew);
		return ResponseEntity.ok(tipoLocalidade);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTipoLocalidade(@PathVariable Integer id){
		tipoLocalidadeService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
