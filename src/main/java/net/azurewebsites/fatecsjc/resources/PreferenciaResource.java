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

import net.azurewebsites.fatecsjc.entity.Preferencia;
import net.azurewebsites.fatecsjc.service.PreferenciaService;

@CrossOrigin
@RestController
@RequestMapping(value = "/preferencia")
public class PreferenciaResource {
	@Autowired
	PreferenciaService preferenciaService;
	
	@RequestMapping(value="/buscapreferencia/{id}", method = RequestMethod.GET)
	public ResponseEntity<Preferencia> findById(@PathVariable Integer id){
		Preferencia preferencia = preferenciaService.findById(id);
		return ResponseEntity.ok(preferencia);
	}
	
	@RequestMapping(value = "/buscapreferencia", method = RequestMethod.GET)
	public ResponseEntity<List<Preferencia>> findAll(){
		List<Preferencia> preferencias = preferenciaService.findAll();
		return ResponseEntity.ok().body(preferencias);
	}
	
	@RequestMapping(value="/buscapreferencia/descricao/{descricao}",method = RequestMethod.GET)
	public ResponseEntity<List<Preferencia>> findByDescricaoContains(@PathVariable String descricao){
		List<Preferencia> preferencias = preferenciaService.findByDescricao(descricao);
		return ResponseEntity.ok(preferencias);
	}
	
	@RequestMapping(value="/buscapreferencia/nome/{nome}",method = RequestMethod.GET)
	public ResponseEntity<List<Preferencia>> findByNome(@PathVariable String nome){
		List<Preferencia> preferencias = preferenciaService.findByNome(nome);
		return ResponseEntity.ok(preferencias);
	}
	
	@RequestMapping(value="/buscapreferencia/ids/{ids}",method = RequestMethod.GET)
	public ResponseEntity<List<Preferencia>> findByArrayId(@PathVariable List<Integer> ids){
		List<Preferencia> preferencias = preferenciaService.findByArrayId(ids);
		return ResponseEntity.ok(preferencias);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/insert/",method = RequestMethod.POST)
	public ResponseEntity<Void> createPreferencia(@RequestBody Preferencia preferenciaNew) {
		Preferencia preferencia = preferenciaService.createPreferencia(preferenciaNew);
		URI uri = URI.create("/buscapreferencia" + "/buscapreferencia/" + preferencia.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<Preferencia> updatePreferencia(@RequestBody Preferencia preferenciaNew) {
		Preferencia preferencia = preferenciaService.updatePreferencia(preferenciaNew);
		return ResponseEntity.ok(preferencia);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePreferencia(@PathVariable Integer id){
		preferenciaService.deletePreferencia(id);
		return ResponseEntity.noContent().build();
	}
}
