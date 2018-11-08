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

import net.azurewebsites.fatecsjc.entity.TipoContato;
import net.azurewebsites.fatecsjc.service.TipoContatoService;

@CrossOrigin
@RestController
@RequestMapping(value = "/tipocontato")
public class TipoContatoResource {
	@Autowired
	TipoContatoService tipoContatoService;
	
	@RequestMapping(value="/buscatipocontato/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoContato> findById(@PathVariable Integer id){
		TipoContato tipoContato = tipoContatoService.findById(id);
		return ResponseEntity.ok(tipoContato);
	}
	
	@RequestMapping(value = "/buscatipocontato", method = RequestMethod.GET)
	public ResponseEntity<List<TipoContato>> findAll(){
		List<TipoContato> tipoContato = tipoContatoService.findAll();
		return ResponseEntity.ok().body(tipoContato);
	}
	
	@RequestMapping(value="/buscatipocontato/descricao/{descricao}",method = RequestMethod.GET)
	public ResponseEntity<List<TipoContato>> findByDescricaoContains(@PathVariable String descricao){
		List<TipoContato> tipoContato = tipoContatoService.findBydescricao(descricao);
		return ResponseEntity.ok(tipoContato);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/insert/{descricao}",method = RequestMethod.POST)
	public ResponseEntity<Void> createTipoContato(@PathVariable String descricao) {
		TipoContato tipoContato = tipoContatoService.create(descricao);
		URI uri = URI.create("/tipocontato" + "/buscatipocontato/" + tipoContato.getId());
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<TipoContato> updateTipoContato(@RequestBody TipoContato tipoContatoNew) {
		TipoContato tipoContato = tipoContatoService.updateTipoContato(tipoContatoNew);
		return ResponseEntity.ok(tipoContato);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTipoContato(@PathVariable Integer id){
		tipoContatoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
