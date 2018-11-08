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
public class Localidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String pontoReferencia;
	
	private Integer numero;
	
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name = "TIPOLOCALIDADE_ID")
	private TipoLocalidade tipoLocalidade;
	
	@ManyToOne
	@JoinColumn(name = "CEP_ID")
	private Cep	cep;
	
	public Localidade(Integer id, String nome, String pontoReferencia, Integer numero, String complemento, 
			TipoLocalidade tipoLocalidade, Cep cep) {
		super();
		this.id = id;
		this.nome = nome;
		this.pontoReferencia = pontoReferencia;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoLocalidade = tipoLocalidade;
		this.cep = cep;
	}
	
}
