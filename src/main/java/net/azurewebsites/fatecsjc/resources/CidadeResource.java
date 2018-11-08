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

import net.azurewebsites.fatecsjc.entity.Cidade;
import net.azurewebsites.fatecsjc.service.CidadeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/cidade")
public class CidadeResource {
	@Autowired
	CidadeService cidadeService;
	
	@RequestMapping(value="/buscacidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cidade> findById(@PathVariable Integer id){
		Cidade cidade = cidadeService.findById(id);
		return ResponseEntity.ok(cidade);
	}
	
	@RequestMapping(value = "/buscacidade", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findAll(){
		List<Cidade> cidade = cidadeService.findAll();
		return ResponseEntity.ok().body(cidade);
	}
	
	@RequestMapping(value="/buscacidade/id/{id}/nome/{nome}",method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findByNomeContainsAndUf(@PathVariable Integer id, @PathVariable String nome){
		List<Cidade> cidade = cidadeService.findByNomeContainsAndUf(id, nome);
		return ResponseEntity.ok(cidade);
	}
	
	@RequestMapping(value="/buscacidade/nome/{nome}/uf/{uf}",method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findByNomeAndUf(@PathVariable String nome, @PathVariable String uf){
		List<Cidade> cidade = cidadeService.findByNomeCidadeEstadoSigla(nome, uf);
		return ResponseEntity.ok(cidade);
	}
	
	@RequestMapping(value="/buscacidade/uf/{uf}",method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findByUf(@PathVariable String uf){
		List<Cidade> cidade = cidadeService.findByEstado(uf);
		return ResponseEntity.ok(cidade);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public ResponseEntity<Void> createCidade(@RequestBody Cidade cidadeNew) {
		Cidade cidade = cidadeService.createCidade(cidadeNew);
		URI uri = URI.create("/cidade" + "/buscacidade/" + cidade.getId());
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<Cidade> updateCidade(@RequestBody Cidade cidadeNew) {
		Cidade cidade = cidadeService.updateCidade(cidadeNew);
		return ResponseEntity.ok(cidade);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCidade(@PathVariable Integer id){
		cidadeService.DeletarCidade(id);
		return ResponseEntity.noContent().build();
	}
}
