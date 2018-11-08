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

import net.azurewebsites.fatecsjc.entity.Estado;
import net.azurewebsites.fatecsjc.service.EstadoService;

@CrossOrigin
@RestController
@RequestMapping(value = "/estado")
public class EstadoResource {
	@Autowired
	EstadoService estadoService;
	
	@RequestMapping(value="/buscaestado/{id}", method = RequestMethod.GET)
	public ResponseEntity<Estado> findById(@PathVariable Integer id){
		Estado estado = estadoService.findById(id);
		return ResponseEntity.ok(estado);
	}
	
	@RequestMapping(value = "/buscaestado", method = RequestMethod.GET)
	public ResponseEntity<List<Estado>> findAll(){
		List<Estado> estado = estadoService.findAll();
		return ResponseEntity.ok().body(estado);
	}
	
	@RequestMapping(value="/buscaestado/nome/{nome}",method = RequestMethod.GET)
	public ResponseEntity<Estado> findByNome(@PathVariable String nome){
		Estado estado = estadoService.findByNome(nome);
		return ResponseEntity.ok(estado);
	}
	
	@RequestMapping(value="/buscaestado/uf/{uf}",method = RequestMethod.GET)
	public ResponseEntity<Estado> findByUf(@PathVariable String uf){
		Estado estado = estadoService.findByUf(uf);
		return ResponseEntity.ok(estado);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public ResponseEntity<Void> createEstado(@RequestBody Estado estadoNew) {
		Estado estado = estadoService.createEstado(estadoNew);
		URI uri = URI.create("/estado" + "/buscaestado/" + estado.getId());
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<Estado> updateEstado(@RequestBody Estado estadoNew) {
		Estado estado = estadoService.updateEstado(estadoNew);
		return ResponseEntity.ok(estado);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEstado(@PathVariable Integer id){
		estadoService.deleteEstado(id);
		return ResponseEntity.noContent().build();
	}
}
