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

import net.azurewebsites.fatecsjc.entity.Responsavel;
import net.azurewebsites.fatecsjc.entity.dto.ResponsavelFisicoDTO;
import net.azurewebsites.fatecsjc.entity.dto.ResponsavelJuridicoDTO;
import net.azurewebsites.fatecsjc.service.ResponsavelService;

@CrossOrigin
@RestController
@RequestMapping(value="/responsavel")
public class ResponsavelResources {
	
	@Autowired
	ResponsavelService responsavelService;
	
	@RequestMapping(value="/buscaresponsavel", method = RequestMethod.GET)	
	public ResponseEntity<List<Responsavel>> findAll(){
		List<Responsavel> responsaveis = responsavelService.findAll();
		return ResponseEntity.ok().body(responsaveis);
	}
	
	@RequestMapping(value="/buscaresponsavel/{id}", method = RequestMethod.GET)
	public ResponseEntity<Responsavel> findById(@PathVariable String id){
		Responsavel responsavel = responsavelService.findById(Integer.parseInt(id));
		return ResponseEntity.ok().body(responsavel);
	}

	@RequestMapping(value="/buscaresponsavel/idtiporesponsavel/{id}/nome/{nome}",method = RequestMethod.GET)
	public ResponseEntity<List<Responsavel>> findByNomeContainsAndType(@PathVariable Integer id, @PathVariable String nome){
		List<Responsavel> responsavel = responsavelService.findByNomeContainsAndType(id, nome);
		return ResponseEntity.ok(responsavel);
	}
	
	@RequestMapping(value="/buscaresponsavel/tipo/{tipo}", method = RequestMethod.GET)
	public ResponseEntity<List<Responsavel>> findByTipo(@PathVariable String tipo){
		List<Responsavel> responsaveis = responsavelService.findByTipoResponsavel(Integer.parseInt(tipo));
		return ResponseEntity.ok().body(responsaveis);
	}
	
	@RequestMapping(value="/buscaresponsavel/cpf/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Responsavel> findByCpf(@PathVariable String cpf){
		Responsavel responsavel = responsavelService.findByCpf(cpf);
		return ResponseEntity.ok().body(responsavel);
	}
	
	@RequestMapping(value="/buscaresponsavel/cnpj/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<Responsavel> findByCnpj(@PathVariable String cnpj){
		Responsavel responsavel = responsavelService.findByCnpj(cnpj);
		return ResponseEntity.ok().body(responsavel);
	}
	
	@RequestMapping(value="/buscaresponsavel/razaosocial/{razaoSocial}", method = RequestMethod.GET)
	public ResponseEntity<List<Responsavel>> findByRazaoSocial(@PathVariable String razaoSocial){
		List<Responsavel> responsaveis = responsavelService.findByRazaoSocial(razaoSocial);
		return ResponseEntity.ok().body(responsaveis);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/insererespfis",method = RequestMethod.POST)
	public Integer createResponsavelFisico(@RequestBody ResponsavelFisicoDTO resDto){
		Responsavel responsavel = responsavelService.createResponsavelFisico(resDto);
		Integer newId = responsavel.getId();
		return newId;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value = "/insererespjur",method = RequestMethod.POST)
	public Integer createResponsavelJuridico(@RequestBody ResponsavelJuridicoDTO resDto){
		Responsavel responsavel = responsavelService.createResponsavelJuridico(resDto);
		Integer newId = responsavel.getId();
		return newId;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteResponsavel(@PathVariable Integer id){
		responsavelService.deleteResponsavel(responsavelService.findById(id));
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADM')")
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<Responsavel> updateResponsavel(@RequestBody Responsavel newResp){
		Responsavel responsavel = responsavelService.updateResponsavel(newResp);
		return ResponseEntity.ok(responsavel);
	}

}
