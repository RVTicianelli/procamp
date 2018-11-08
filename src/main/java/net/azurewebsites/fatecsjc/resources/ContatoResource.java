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

import net.azurewebsites.fatecsjc.entity.Contato;
import net.azurewebsites.fatecsjc.entity.dto.ContatoLocalidadeDTO;
import net.azurewebsites.fatecsjc.entity.dto.ContatoResponsavelDTO;
import net.azurewebsites.fatecsjc.service.ContatoService;

@CrossOrigin
@RestController
@RequestMapping(value="/contato")
public class ContatoResource {
	
	@Autowired
	ContatoService contatoService;
	
	@RequestMapping(value="/buscacontato", method = RequestMethod.GET)	
	public ResponseEntity<List<Contato>> findAll(){
		List<Contato> contatos = contatoService.findAll();
		return ResponseEntity.ok().body(contatos);
	}
	
	@RequestMapping(value="/buscacontato/{id}", method = RequestMethod.GET)
	public ResponseEntity<Contato> findById(@PathVariable String id){
		Contato contato = contatoService.findById(Integer.parseInt(id));
		return ResponseEntity.ok().body(contato);
	}
	
	@RequestMapping(value="/buscacontato/contato/{contato}", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> findByContato(@PathVariable String contato){
		List<Contato> contatos = contatoService.findByContato(contato);
		return ResponseEntity.ok().body(contatos);
	}
	
	@RequestMapping(value="/buscacontato/valor/{valor}", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> findByValor(@PathVariable String valor){
		List<Contato> contatos = contatoService.findByValor(valor);
		return ResponseEntity.ok().body(contatos);
	}
	
	@RequestMapping(value="/buscacontato/tipo/{tipo}", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> findByTipo(@PathVariable String tipo){
		List<Contato> contatos = contatoService.findByTipoContato(Integer.parseInt(tipo));
		return ResponseEntity.ok().body(contatos);
	}
	
	@RequestMapping(value="/buscacontato/localidade/{localidade}", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> findByLocalidade(@PathVariable String localidade){
		List<Contato> contatos = contatoService.findByLocalidade(Integer.parseInt(localidade));
		return ResponseEntity.ok().body(contatos);
	}

	@RequestMapping(value="/buscacontato/responsavel/{responsavel}", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> findByResponsavel(@PathVariable String responsavel){
		List<Contato> contatos = contatoService.findByResponsavel(Integer.parseInt(responsavel));
		return ResponseEntity.ok().body(contatos);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/inserecontlocal",method = RequestMethod.POST)
	public ResponseEntity<Void> createContatoLocalidade(@RequestBody ContatoLocalidadeDTO contLocDto){
		Contato contato = contatoService.createContatoLocalidade(contLocDto);
		URI uri = URI.create("/contato" + "/buscacontato/" + contato.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/inserecontresp",method = RequestMethod.POST)
	public ResponseEntity<Void> createContatoResponsavel(@RequestBody ContatoResponsavelDTO contResDto){
		Contato contato = contatoService.createContatoResponsavel(contResDto);
		URI uri = URI.create("/contato" + "/buscacontato/" + contato.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContato(@PathVariable Integer id){
		contatoService.deleteContato(contatoService.findById(id));
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<Contato> updateContato(@RequestBody Contato newCont){
		Contato contato = contatoService.updateContato(newCont);
		return ResponseEntity.ok(contato);
	}

}
