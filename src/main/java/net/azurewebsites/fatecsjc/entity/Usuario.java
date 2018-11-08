package net.azurewebsites.fatecsjc.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import net.azurewebsites.fatecsjc.enums.Perfil;

@Getter
@Setter
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String sexo;
	private String email;
	private String ultimoLogin;
	
	@JsonIgnore
	private String senha;
	
	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_TIPOCAMPANHA", joinColumns = {@JoinColumn(name = "USUARIO_ID")}, inverseJoinColumns = {
			@JoinColumn(name = "TIPOCAMPANHA_ID")})
	private Set<TipoCampanha> tipoCampanha;;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getId());
	}
	
	public Usuario () {
		addPerfil(Perfil.CLIENTE);
	}
	
	public Usuario (Integer id, String nome, String dataNascimento, String cpf, String sexo, String email, String senha, String ultimoLogin) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.sexo = sexo;
		this.email = email;
		this.senha = senha;
		this.ultimoLogin = ultimoLogin;
		addPerfil(Perfil.CLIENTE);
	}
}
