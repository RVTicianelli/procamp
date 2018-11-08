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

import net.azurewebsites.fatecsjc.entity.TipoCampanha;
import net.azurewebsites.fatecsjc.service.TipoCampanhaService;

@CrossOrigin
@RestController
@RequestMapping(value = "/tipocampanha")
public class TipoCampanhaResource {
	@Autowired
	TipoCampanhaService tipoCampanhaService;
	
	@RequestMapping(value="/buscatipocampanha/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoCampanha> findById(@PathVariable Integer id){
		TipoCampanha tipoCampanha = tipoCampanhaService.findById(id);
		return ResponseEntity.ok(tipoCampanha);
	}
	
	@RequestMapping(value = "/buscatipocampanha", method = RequestMethod.GET)
	public ResponseEntity<List<TipoCampanha>> findAll(){
		List<TipoCampanha> tipoCampanha = tipoCampanhaService.findAll();
		return ResponseEntity.ok().body(tipoCampanha);
	}
	
	@RequestMapping(value="/buscatipocampanha/descricao/{descricao}",method = RequestMethod.GET)
	public ResponseEntity<List<TipoCampanha>> findByDescricaoContains(@PathVariable String descricao){
		List<TipoCampanha> tipoCampanha = tipoCampanhaService.findBydescricao(descricao);
		return ResponseEntity.ok(tipoCampanha);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/insert/{descricao}",method = RequestMethod.POST)
	public ResponseEntity<Void> createTipoLocalidade(@PathVariable String descricao) {
		TipoCampanha tipoCampanha = tipoCampanhaService.create(descricao);
		URI uri = URI.create("/tipocampanha" + "/buscatipocampanha/" + tipoCampanha.getId());
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update/",method = RequestMethod.PUT)
	public ResponseEntity<TipoCampanha> updatePreferencia(@RequestBody TipoCampanha tipoCampanhaNew) {
		TipoCampanha tipoCampanha = tipoCampanhaService.updateTipoCampanha(tipoCampanhaNew);
		return ResponseEntity.ok(tipoCampanha);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTipoLocalidade(@PathVariable Integer id){
		tipoCampanhaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
