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

import net.azurewebsites.fatecsjc.entity.Usuario;
import net.azurewebsites.fatecsjc.entity.dto.UsuarioDTO;
import net.azurewebsites.fatecsjc.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping(value="/usuario")
public class UsuarioResources {

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value="/buscausuario", method = RequestMethod.GET)	
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> usuarios = usuarioService.findAll();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@RequestMapping(value="/buscausuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable String id){
		Usuario usuario = usuarioService.findById(Integer.parseInt(id));
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value="/buscausuario/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findByNome(@PathVariable String nome){
		List<Usuario> usuarios = usuarioService.findByNome(nome);
		return ResponseEntity.ok().body(usuarios);
	}
	
	@RequestMapping(value="/buscausuario/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findByEmail(@PathVariable String email){
		Usuario usuario = usuarioService.findByEmail(email);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value="/buscausuario/sexo/{sexo}", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findBySexo(@PathVariable String sexo){
		List<Usuario> usuario = usuarioService.findBySexo(sexo);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value="/buscausuario/cpf/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findByCpf(@PathVariable String cpf){
		Usuario usuario = usuarioService.findByCpf(cpf);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value = "/insere",method = RequestMethod.POST)
	public ResponseEntity<Void> createUsuario(@RequestBody UsuarioDTO usuarioNew){
		Usuario usuario = usuarioService.create(usuarioNew);
		URI uri = URI.create("/usuario" + "/buscausuario/" + usuario.getId());
		return ResponseEntity.created(uri).build();
	}
	
		
	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id){
		usuarioService.deleteUsuario(usuarioService.findById(id));
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioNew){
		Usuario usuario = usuarioService.updateUsuario(id, usuarioNew);
		return ResponseEntity.ok(usuario);
	}
}
