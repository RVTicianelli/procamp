package net.azurewebsites.fatecsjc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Usuario;
import net.azurewebsites.fatecsjc.entity.TipoCampanha;
import net.azurewebsites.fatecsjc.entity.dto.UsuarioDTO;
import net.azurewebsites.fatecsjc.repository.UsuarioRepository;
import net.azurewebsites.fatecsjc.service.exception.DataIntegrityException;
import net.azurewebsites.fatecsjc.service.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder pwd;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PreferenciaService preferenciaService;
	
	@Autowired
	TipoCampanhaService tipoCampanhaService;
	
	public Usuario findById(Integer id) {
		return usuarioRepository.buscaPorId(id);
	}

	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> findByNome(String nome) {
		Optional<List<Usuario>> usuario = Optional.ofNullable(usuarioRepository.findByNome(nome));
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado!"));
	}
	
	public List<Usuario> findBySexo(String sexo) {
		Optional<List<Usuario>> usuario = Optional.ofNullable(usuarioRepository.findBySexo(sexo));
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado!"));
	}
	
	public Usuario findByEmail(String email) {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmail(email));
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado!"));
	}
	
	public Usuario findByCpf(String cpf) {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByCpf(cpf));
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado!"));
	}
	
	public Usuario findByEmailAndPassword(String email, String senha) {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmailAndPassword(email, pwd.encode(senha)));
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado!"));
	}
	
	public Usuario create(UsuarioDTO usuarioDto) {
		if(usuarioRepository.findByCpfAndEmail(usuarioDto.getCpf(), usuarioDto.getEmail()) == null) {
			Usuario usuario = usuarioFromDto(usuarioDto);
			
			usuarioRepository.save(usuario);
			return findById(usuario.getId());
		}
		else {
			throw new DataIntegrityException("Não foi possível cadastrar este usuário. CPF ou Email ja cadastrados.");
		}
	}
	
	public Usuario updateUsuario(Integer id, UsuarioDTO usuarioDto) {
		if(usuarioRepository.findById(id) != null) {
			Usuario usuario = usuarioFromDto(usuarioDto);
			usuario.setId(id);
			
			return usuarioRepository.save(usuario);
		} else {
			throw new DataIntegrityException("Não foi possível atualizar este usuário.");
		}
	}
	
	public void deleteUsuario(Usuario usuario) {
		Integer id = usuario.getId();
		findById(id);
		try {
			usuarioRepository.delete(findById(id));
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não foi possível excluir este usuário..");
		}
	}
	
	public Usuario usuarioFromDto(UsuarioDTO usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setId(null);
		usuario.setNome(usuarioDto.getNome());
		usuario.setDataNascimento(usuarioDto.getDataNascimento());
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setSexo(usuarioDto.getSexo());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setSenha(pwd.encode(usuarioDto.getSenha()));
		usuario.setUltimoLogin(usuarioDto.getUltimoLogin());
		usuario.setPerfis(usuarioDto.getPerfis());
		
		Set<TipoCampanha> tipoCampanha = new HashSet<>();
		for (Integer id: usuarioDto.getTipoCampanha()) {
			tipoCampanha.add(tipoCampanhaService.findById(id));
		}
				
		usuario.setTipoCampanha(tipoCampanha);
		
		return usuario;
	}

}
