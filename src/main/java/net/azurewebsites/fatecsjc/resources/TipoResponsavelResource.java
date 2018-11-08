package net.azurewebsites.fatecsjc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.azurewebsites.fatecsjc.entity.TipoResponsavel;
import net.azurewebsites.fatecsjc.service.TipoResponsavelService;

@CrossOrigin
@RestController
@RequestMapping(value = "/tiporesponsavel")
public class TipoResponsavelResource {
	@Autowired
	TipoResponsavelService tipoResponsavelService;
	
	@RequestMapping(value="/buscatiporesponsavel/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoResponsavel> findById(@PathVariable Integer id){
		TipoResponsavel tipoResponsavel = tipoResponsavelService.findById(id);
		return ResponseEntity.ok(tipoResponsavel);
	}
	
	@RequestMapping(value = "/buscatiporesponsavel", method = RequestMethod.GET)
	public ResponseEntity<List<TipoResponsavel>> findAll(){
		List<TipoResponsavel> tipoResponsavel = tipoResponsavelService.findAll();
		return ResponseEntity.ok().body(tipoResponsavel);
	}
	
	@RequestMapping(value="/buscatiporesponsavel/descricao/{descricao}",method = RequestMethod.GET)
	public ResponseEntity<List<TipoResponsavel>> findByDescricaoContains(@PathVariable String descricao){
		List<TipoResponsavel> tipoResponsavel = tipoResponsavelService.findBydescricao(descricao);
		return ResponseEntity.ok(tipoResponsavel);
	}

	@RequestMapping(value="/insert/{descricao}",method = RequestMethod.POST)
	public ResponseEntity<Void> createTipoResponsavel(@PathVariable String descricao) {
		TipoResponsavel tipoResponsavel = tipoResponsavelService.create(descricao);
		URI uri = URI.create("/tiporesponsavel" + "/buscatiporesponsavel/" + tipoResponsavel.getId());
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<TipoResponsavel> updateTipoResponsavel(@RequestBody TipoResponsavel tipoResponsavelNew) {
		TipoResponsavel tipoResponsavel = tipoResponsavelService.updateTipoResponsavel(tipoResponsavelNew);
		return ResponseEntity.ok(tipoResponsavel);
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTipoResponsavel(@PathVariable Integer id){
		tipoResponsavelService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
