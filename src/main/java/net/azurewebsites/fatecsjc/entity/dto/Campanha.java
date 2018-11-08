package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.azurewebsites.fatecsjc.entity.Localidade;
import net.azurewebsites.fatecsjc.entity.Preferencia;
import net.azurewebsites.fatecsjc.entity.Responsavel;
import net.azurewebsites.fatecsjc.entity.TipoCampanha;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Campanha implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String dataInicio;
	
	private String dataFim;
	
	private String dataCadastro;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "RESPONSAVEL_ID")
	private Responsavel responsavel;
	
	@ManyToOne
	@JoinColumn(name = "TIPOCAMPANHA_ID")
	private TipoCampanha tipoCampanha;
	
	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(name = "CAMPANHA_LOCALIDADE", joinColumns = {@JoinColumn(name = "CAMPANHA_ID")}, inverseJoinColumns = {
			@JoinColumn(name = "LOCALIDADE_ID")})
	private Set<Localidade> localidades;
	
	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(name = "CAMPANHA_PREFERENCIA", joinColumns = {@JoinColumn(name = "CAMPANHA_ID")}, inverseJoinColumns = {
			@JoinColumn(name = "PREFERENCIA_ID")})
	private Set<Preferencia> preferencias;
	
	
	public Campanha(Integer id, String nome, String dataInicio, String dataFim, String dataCadastro, String descricao, Responsavel responsavel, 
			TipoCampanha tipoCampanha) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.dataCadastro = dataCadastro;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.tipoCampanha = tipoCampanha;
	}

}
