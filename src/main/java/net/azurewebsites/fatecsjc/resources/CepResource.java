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

import net.azurewebsites.fatecsjc.entity.Cep;
import net.azurewebsites.fatecsjc.entity.Cidade;
import net.azurewebsites.fatecsjc.service.CepService;
import net.azurewebsites.fatecsjc.service.CidadeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/ceps")
public class CepResource {
	@Autowired
	CepService cepService;
	
	@Autowired
	CidadeService cidadeService;

	@RequestMapping(value="/buscacep/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cep> findById(@PathVariable Integer id){
		Cep cep = cepService.findById(id);
		return ResponseEntity.ok(cep);
	}
	
	@RequestMapping(value = "/buscacep/cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Cep> findByCep(@PathVariable String cep){
		Cep ceps = cepService.findByCep(cep);
		return ResponseEntity.ok().body(ceps);
	}

	@RequestMapping(value = "/buscaceplist/cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<List<Cep>> findByCepList(@PathVariable String cep){
		List <Cep> ceps = cepService.findByCepList(cep);
		return ResponseEntity.ok().body(ceps);
	}
	
	@RequestMapping(value = "/buscacep", method = RequestMethod.GET)
	public ResponseEntity<List<Cep>> findAll(){
		List<Cep> ceps = cepService.findAll();
		return ResponseEntity.ok().body(ceps);
	}
	
	
	@RequestMapping(value = "/buscacep/cidade/{cidade}", method = RequestMethod.GET)
	public ResponseEntity<List<Cep>> findByCidade(@PathVariable String cidade){
		List<Cep> ceps = cepService.findByCidade(cidade);
		return ResponseEntity.ok().body(ceps);
	}
	
	@RequestMapping(value = "/buscacep/rua/{nomeRua}", method = RequestMethod.GET)
	public ResponseEntity<List<Cep>> findByNomeRua(@PathVariable String nomeRua){
		List<Cep>ceps = cepService.findByNomeRua(nomeRua);
		return ResponseEntity.ok().body(ceps);
	}
	
	@RequestMapping(value = "/buscacep/cidadeEstado/{estadoSigla}", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findByEstado(@PathVariable String estadoSigla){
		List<Cidade> cidades = cidadeService.findByEstado(estadoSigla);
		return ResponseEntity.ok().body(cidades);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public ResponseEntity<Void> createCep(@RequestBody Cep cepNew) {
		Cep cep = cepService.createCep(cepNew);
		URI uri = URI.create("/ceps" + "/buscacep/" + cep.getId());
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<Cep> updateCep(@RequestBody Cep cepNew) {
		Cep cep = cepService.updateCep(cepNew);
		return ResponseEntity.ok(cep);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCep(@PathVariable Integer id){
		cepService.deletarCep(id);
		return ResponseEntity.noContent().build();
	}


}
