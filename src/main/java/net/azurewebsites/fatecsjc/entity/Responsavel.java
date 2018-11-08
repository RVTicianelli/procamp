package net.azurewebsites.fatecsjc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Responsavel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String cpf;
	
	private String cnpj;
	
	private String dataNascimento;
	
	private String razaoSocial;
	
	private String sexo;
	
	private Integer numero;
	
	private String complemento; 

	@ManyToOne 
	@JoinColumn(name = "TIPORESPONSAVEL_ID") 
	private TipoResponsavel	tipoResponsavel;
	
	@ManyToOne 
	@JoinColumn(name = "CEP_ID") 
	private Cep	cep; 
	
	public Responsavel(Integer id, String nome, String cpf, String dataNascimento, String sexo, Integer numero, 
			String complemento, TipoResponsavel	tipoResponsavel, Cep cep) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoResponsavel = tipoResponsavel; 
		this.cep = cep;
	}
	
	public Responsavel(Integer id, String nome, String cnpj, String razaoSocial, Integer numero, 
			String complemento, TipoResponsavel	tipoResponsavel, Cep cep) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoResponsavel = tipoResponsavel; 
		this.cep = cep;
	}
	

}
